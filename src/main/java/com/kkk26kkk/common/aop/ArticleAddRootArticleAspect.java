package com.kkk26kkk.common.aop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kkk26kkk.bbs.dao.ArticleDao;
import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleParam;
import com.kkk26kkk.bbs.model.RootArticleDecorator;
import com.kkk26kkk.bbs.model.User;
import com.kkk26kkk.common.model.PageList;

@Aspect
@Component
public class ArticleAddRootArticleAspect {
	@Autowired
	private ArticleDao articleDao;
	
	@Around("@annotation(com.kkk26kkk.common.aop.AddRootArticle)")
	public Object addRootArticle(ProceedingJoinPoint joinPoint)	{
		Object obj = null;
		try {
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		List<Article> articleList = null;
		if (obj instanceof PageList) {
			articleList = ((PageList<Article>) obj).getList();
		} else if (obj instanceof List) {
			articleList = (List<Article>) obj;
		} else if (obj instanceof Article) {
			articleList = new LinkedList<>();
			articleList.add((Article) obj);
		} else {	
			return obj;
		}
		
		String rootIds = articleList.stream()
				.map(article -> article.getRootId())
				.collect(Collectors.joining(","));
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        User user = (User) request.getSession().getAttribute("user");
				
		ArticleParam articleParam = new ArticleParam
				.Builder(0)
				.userGrade(user.getUserGrade())
				.articleId(rootIds)
				.build();
		
		List<Article> parentArticleList = articleDao.selectParentArticleList(articleParam);

		Map<String, Article> parentArticleMap = new HashMap<>();
		for(Article article : parentArticleList) {
			parentArticleMap.put(article.getArticleId(), article);
		}
		
		for(int i = 0 ; i < articleList.size(); i++) {
			Article article = articleList.get(i);
			
			Article parentArticle = parentArticleMap.get(article.getArticleId());
			if(null == parentArticle) {
				continue;
			}
			
			articleList.set(i, new RootArticleDecorator(article, parentArticle));
		}
		
		if(obj instanceof PageList) {
			((PageList<Article>) obj).setList(articleList);
		}
		return obj;
	}
}
