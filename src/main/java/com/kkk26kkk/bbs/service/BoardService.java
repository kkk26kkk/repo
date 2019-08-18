package com.kkk26kkk.bbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.BoardDao;
import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleParam;
import com.kkk26kkk.bbs.model.Comment;
import com.kkk26kkk.bbs.model.CommentParam;
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
		PageList<Article> pageArticleList = boardDao.getFeedArticleList(articleParam);
		return pageArticleList;
	}

	public PageList<Comment> getFeedCommentList(CommentParam commentParam) {
		PageList<Comment> pageCommentList = boardDao.getFeedCommentList(commentParam);
		return pageCommentList;
	}
}