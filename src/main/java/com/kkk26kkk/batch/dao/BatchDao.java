package com.kkk26kkk.batch.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.batch.model.ArticleRank;

@Repository
public class BatchDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String NAME_SPACE = "com.kkk26kkk.batch.dao.BatchDao.";
	
	public List<ArticleRank> selectArticleIdListForRank(int limit) {
		return sqlSession.selectList(NAME_SPACE + "selectArticleIdListForRank", limit);
	}
	
	public List<Map<String, Object>> selectReadCountList(String articleIds) {
		return sqlSession.selectList(NAME_SPACE + "selectReadCountList", articleIds);
	}

	public List<Map<String, Object>> selectCommentCountList(String articleIds) {
		return sqlSession.selectList(NAME_SPACE + "selectCommentCountList", articleIds);
	}
	
	public int insertArticleRank(List<ArticleRank> articleRankList) {
		return sqlSession.insert(NAME_SPACE + "insertArticleRank", articleRankList);
	}
	
	public int deleteArticleRank() {
		return sqlSession.delete(NAME_SPACE + "deleteArticleRank");
	}
	
}
