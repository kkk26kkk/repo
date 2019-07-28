package com.kkk26kkk.bbs.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.Comment;

@Repository
public class CommentDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public void insertComment(Comment comment) {
		sqlSession.insert("insertComment", comment);
	}

	public List<Comment> getCommentList(int articleId) {
		return sqlSession.selectList("getCommentList", articleId);
	}
}
