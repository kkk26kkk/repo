package com.kkk26kkk.bbs.service;

import java.util.List;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.BoardDao;
import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleParam;
import com.kkk26kkk.common.exception.BizException;
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

	public PageList<Article> getFeedList(ArticleParam articleParam) throws BizException {
		PageList<Article> pageList = boardDao.getFeedArticleListAddCommentsMore(articleParam);
		if(null == pageList) {
			throw new BizException("게시글을 가져올 수 없습니다.");
		}
		return pageList;
	}
	
	public PageList<Article> getClipboardList(ArticleParam articleParam) throws BizException {
		PageList<Article> pageList = boardDao.getClipboardListAddCommentsMore(articleParam);
		if(null == pageList) {
			throw new BizException("게시글을 가져올 수 없습니다.");
		}
		return pageList;
	}
	
}