package com.kkk26kkk.bbs.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.CommentDao;
import com.kkk26kkk.bbs.model.Comment;
import com.kkk26kkk.bbs.model.CommentDto;
import com.kkk26kkk.bbs.model.CommentParam;
import com.kkk26kkk.bbs.model.CommentVo;
import com.kkk26kkk.bbs.model.User;

@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;
	
	public int insertComment(CommentDto commentDto, User user) throws SQLException {
		CommentVo commentVo = new CommentVo();
		commentVo.setUserId(user.getUserId());
		commentVo.setUserName(user.getUserName());
		commentVo.setArticleId(commentDto.getArticleId());
		commentVo.setContents(commentDto.getContents());
		
		int resultCnt = commentDao.insertComment(commentVo);
		if(1 != resultCnt) {
			throw new SQLException("댓글 등록을 실패 했습니다.");
		}
		
		int commentId = commentDao.getCommentSeqNextVal();
		
		return commentId;
	}
	
		
	public List<Comment> getCommentList(CommentParam commentParam) {
		return commentDao.getCommentList(commentParam);
	}

	public Comment getComment(int commentId) {
		return commentDao.getComment(commentId);
	}
}
