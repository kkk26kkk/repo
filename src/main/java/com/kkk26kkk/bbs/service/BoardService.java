package com.kkk26kkk.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.BoardDao;
import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.common.model.PageList;
import com.kkk26kkk.common.model.PageListParam;

@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;

	public PageList<Article> getArticleList(PageListParam pageListParam) {
		return boardDao.getArticleList(pageListParam);
	}
	
	public List<Article> getArticleListMore(PageListParam pageListParam) {
		return boardDao.getArticleListMore(pageListParam);
	}
}