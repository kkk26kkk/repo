package com.kkk26kkk.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.BoardDao;
import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.common.model.PageListParam;
import com.kkk26kkk.common.model.PageList;

@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;

	public PageList<Article> getArticleList(PageListParam pageDto) {
		return boardDao.getArticleList(pageDto);
	}

}