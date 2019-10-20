package com.kkk26kkk.common.aop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkk26kkk.bbs.article.dao.BoardDao;
import com.kkk26kkk.bbs.article.model.Article;
import com.kkk26kkk.bbs.article.model.ArticleParam;
import com.kkk26kkk.bbs.article.model.RootArticleDecorator;
import com.kkk26kkk.common.model.PageList;

@Aspect
@Component
public class ArticleAddRootArticleAspect {
	@Autowired
	private BoardDao BoardDao;
	
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
		
		List<Article> parentArticleList = BoardDao.selectParentArticleList(rootIds);

		Map<String, Article> parentArticleMap = new HashMap<>();
		for(Article article : parentArticleList) {
			parentArticleMap.put(article.getArticleId(), article);
		}
		
		for(int i = 0 ; i < articleList.size(); i++) {
			Article article = articleList.get(i);
			
			Article parentArticle = parentArticleMap.get(article.getRootId());
			if(null == parentArticle) {
				continue;
			}
			
			if(StringUtils.equals(article.getArticleId(), parentArticle.getArticleId())) {
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
