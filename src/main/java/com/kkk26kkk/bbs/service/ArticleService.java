package com.kkk26kkk.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.ArticleDao;
import com.kkk26kkk.bbs.model.Article;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	public Article getArticle(int articleId) {
		return articleDao.getArticle(articleId);
	}

	public void insertArticle(Article article) {
		articleDao.insertArticle(article);
	}
	
	public void updateArticle(Article article) {
		articleDao.updateArticle(article);
	}

	public void deleteArticle(int articleId) {
		articleDao.deleteArticle(articleId);
	}
}
