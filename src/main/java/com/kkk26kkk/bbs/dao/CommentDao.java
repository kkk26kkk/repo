package com.kkk26kkk.bbs.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.Comment;
import com.kkk26kkk.bbs.model.CommentParam;
import com.kkk26kkk.bbs.model.CommentVo;
import com.kkk26kkk.common.dao.BaseDao;
import com.kkk26kkk.common.model.PageList;

@Repository
public class CommentDao extends BaseDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int insertComment(CommentVo commentVo) {
		return sqlSession.insert("insertComment", commentVo);
	}

	public PageList<Comment> getCommentList(CommentParam commentParam) {
//		return sqlSession.selectList("getCommentList", commentParam);
		return super.getPageListMore("getCommentList", commentParam);
	}

	public Comment getComment(String commentId) {
		return sqlSession.selectOne("getComment", commentId);
	}

	public String getCommentSeqNextVal() {
		return sqlSession.selectOne("getCommentSeqNextVal");
	}
}
