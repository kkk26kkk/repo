package com.kkk26kkk.bbs.comment.dao;

import java.util.Map;
import java.util.function.Function;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.comment.model.Comment;
import com.kkk26kkk.bbs.comment.model.CommentParam;
import com.kkk26kkk.bbs.comment.model.CommentVo;
import com.kkk26kkk.common.dao.BaseDao;
import com.kkk26kkk.common.model.PageList;

@Repository
public class CommentDao extends BaseDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String NAME_SPACE = "com.kkk26kkk.bbs.comment.dao.CommentDao.";

	public int insertComment(CommentVo commentVo) {
		return sqlSession.insert(NAME_SPACE + "insertComment", commentVo);
	}

	public PageList<Comment> selectCommentList(CommentParam commentParam) {
//		return sqlSession.selectList("selectCommentList", commentParam);
		return super.selectPageListMore(NAME_SPACE + "selectCommentList", commentParam);
	}
	
	public Map<String, PageList<Comment>> selectCommentListMapMore(CommentParam commentParam, Function<Comment, String> groupById) {
		return super.selectPageListMapMore(NAME_SPACE + "selectFeedCommentList", commentParam, groupById);
	}
	
	public Map<String, PageList<Comment>> selectCommentListMapTotal(CommentParam commentParam, Function<Comment, String> groupById) {
		return super.selectPageListMapTotal(NAME_SPACE + "selectFeedCommentList", NAME_SPACE + "selectCommentTotalCount", commentParam, groupById);
	}

	public Comment selectComment(String commentId) {
		return sqlSession.selectOne(NAME_SPACE + "selectComment", commentId);
	}

	public String selectCommentSeqNextVal() {
		return sqlSession.selectOne(NAME_SPACE + "selectCommentSeqNextVal");
	}
}
