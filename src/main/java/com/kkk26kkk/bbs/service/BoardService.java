package com.kkk26kkk.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.BoardDao;
import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.common.model.Page;

@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;

	public List<Article> getArticleList(Page page) {
		return boardDao.getArticleList(page);
	}
	
	public int getArticleCount() {
		return boardDao.getArticleCount();
	}

}
