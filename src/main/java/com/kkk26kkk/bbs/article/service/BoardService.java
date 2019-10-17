package com.kkk26kkk.bbs.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.article.dao.BoardDao;
import com.kkk26kkk.bbs.article.model.Article;
import com.kkk26kkk.bbs.article.model.ArticleParam;
import com.kkk26kkk.common.exception.BizException;
import com.kkk26kkk.common.model.PageList;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	public PageList<Article> getArticleList(ArticleParam articleParam) {
		return boardDao.selectArticleList(articleParam);
	}
	
	public List<Article> getArticleListMore(ArticleParam articleParam) {
		return boardDao.selectArticleListMore(articleParam);
	}

	public PageList<Article> getFeedList(ArticleParam articleParam) throws BizException {
		PageList<Article> pageList = boardDao.selectFeedList(articleParam);
		if(null == pageList) {
			throw new BizException("게시글을 가져올 수 없습니다.");
		}
		return pageList;
	}
	
	public PageList<Article> getClipboardList(ArticleParam articleParam) throws BizException {
		PageList<Article> pageList = boardDao.selectClipboardList(articleParam);
		if(null == pageList) {
			throw new BizException("게시글을 가져올 수 없습니다.");
		}
		return pageList;
	}
	
}