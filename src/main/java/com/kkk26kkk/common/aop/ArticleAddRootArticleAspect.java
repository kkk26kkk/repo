package com.kkk26kkk.common.aop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkk26kkk.bbs.dao.ArticleDao;
import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleParam;
import com.kkk26kkk.bbs.model.RootArticle;
import com.kkk26kkk.bbs.model.XArticle;
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
		
		String rootIdList = articleList.stream()
				.map(a -> ((XArticle) a).getRootId())
				.collect(Collectors.joining(","));
				
		ArticleParam articleParam = new ArticleParam
				.Builder(0)
				.articleId(rootIdList)
				.build();
		
		List<Article> parentArticleList = articleDao.selectParentArticleList(articleParam);

		Map<String, Article> parentArticleListMap = new HashMap<>();
		for(Article article : parentArticleList) {
			parentArticleListMap.put(article.getArticleId(), article);
		}
		
		articleList = articleList.stream()
				.filter(a -> ((XArticle) a).getRootId() != a.getArticleId())
				.map(a -> new RootArticle(a, parentArticleListMap.get(((XArticle) a).getRootId())))
				.collect(Collectors.toList());
		
		if(obj instanceof PageList) {
			((PageList<Article>) obj).setList(articleList);
		}
		return obj;
	}
}
