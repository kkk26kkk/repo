package com.kkk26kkk.bbs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.BoardDao;
import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleParam;
import com.kkk26kkk.common.model.PageList;

@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;

	public PageList<Article> getArticleList(ArticleParam articleParam) {
		return boardDao.getArticleList(articleParam);
	}
	
	public List<Article> getArticleListMore(ArticleParam articleParam) {
		return boardDao.getArticleListMore(articleParam);
	}

	public PageList<Article> getFeedList(ArticleParam articleParam) {
		PageList<Article> pageArticleList = boardDao.getFeedArticleListAddCommentsMore(articleParam);
		return pageArticleList;
	}

	public String getArticleIdList(String userId) {
		List<String> list = boardDao.getArticleIdList(userId);
		String articleIdList = list.stream().collect(Collectors.joining(","));
		
		return articleIdList;
	}

	public PageList<Article> getClipboardList(ArticleParam articleParam) {
		PageList<Article> pageClipboardList = boardDao.getClipboardListAddCommentsMore(articleParam);
		return pageClipboardList;
	}
	
}