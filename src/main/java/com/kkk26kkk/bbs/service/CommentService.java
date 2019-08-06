package com.kkk26kkk.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.CommentDao;
import com.kkk26kkk.bbs.model.Comment;

@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;
	
	public void insertComment(Comment comment) {
		commentDao.insertComment(comment);
	}
	
	public List<Comment> getCommentList(int articleId) {
		return commentDao.getCommentList(articleId);
	}
}
