package com.kkk26kkk.bbs.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.comment.dao.CommentDao;
import com.kkk26kkk.bbs.comment.model.Comment;
import com.kkk26kkk.bbs.comment.model.CommentDto;
import com.kkk26kkk.bbs.comment.model.CommentParam;
import com.kkk26kkk.bbs.comment.model.CommentVo;
import com.kkk26kkk.common.code.Code;
import com.kkk26kkk.common.exception.BizException;
import com.kkk26kkk.common.model.PageList;
import com.kkk26kkk.user.model.User;

@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;
	
	public String insertComment(CommentDto commentDto, User user) throws BizException {
		String commentId = commentDao.selectCommentSeqNextVal();
		
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
			throw new BizException("댓글 등록을 실패 했습니다.");
		}
		
		return commentId;
	}
		
	public PageList<Comment> getCommentList(CommentParam commentParam) throws BizException {
		PageList<Comment> pageList = commentDao.selectCommentList(commentParam);
		if(null == pageList) {
			throw new BizException("댓글을 가져올 수 없습니다.");
		}
		return commentDao.selectCommentList(commentParam);
	}

	public Comment getComment(String commentId) {
		return commentDao.selectComment(commentId);
	}
}