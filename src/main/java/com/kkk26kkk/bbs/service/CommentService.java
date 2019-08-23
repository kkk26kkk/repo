package com.kkk26kkk.bbs.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.CommentDao;
import com.kkk26kkk.bbs.model.Comment;
import com.kkk26kkk.bbs.model.CommentDto;
import com.kkk26kkk.bbs.model.CommentParam;
import com.kkk26kkk.bbs.model.CommentVo;
import com.kkk26kkk.bbs.model.User;
import com.kkk26kkk.common.model.Code;
import com.kkk26kkk.common.model.PageList;

@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;
	
	public String insertComment(CommentDto commentDto, User user) throws SQLException {
		String commentId = commentDao.getCommentSeqNextVal();
		
		CommentVo commentVo = new CommentVo();
		commentVo.setCommentId(commentId);
		commentVo.setUserId(user.getUserId());
		commentVo.setUserName(user.getUserName());
		commentVo.setArticleId(commentDto.getArticleId());
		commentVo.setContents(commentDto.getContents());
		
		if(commentDto.isSecret()) {
			commentVo.setCode(Code.COMMENT_SECRET_TYPE_PRIVATE.getCode());
		} else {
			commentVo.setCode(Code.COMMENT_SECRET_TYPE_PUBLIC.getCode());
		}
		
		int resultCnt = commentDao.insertComment(commentVo);
		if(1 != resultCnt) {
			throw new SQLException("댓글 등록을 실패 했습니다.");
		}
		
		
		return commentId;
	}
	
		
	public PageList<Comment> getCommentList(CommentParam commentParam) {
		return commentDao.getCommentList(commentParam);
	}

	public Comment getComment(String commentId) {
		return commentDao.getComment(commentId);
	}
}