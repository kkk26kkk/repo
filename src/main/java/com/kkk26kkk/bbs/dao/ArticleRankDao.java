package com.kkk26kkk.bbs.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.ArticleRank;

@Repository
public class ArticleRankDao {
	private static final String NAME_SPACE = "bbs.articleRank.dao.";
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<ArticleRank> selectArticleIdListForRank(int limit) {
		return sqlSession.selectList(NAME_SPACE + "selectArticleIdListForRank", limit);
	}
	
	public List<Map<String, Object>> selectReadCountList(String articleIds) {
		return sqlSession.selectList(NAME_SPACE + "selectReadCountList", articleIds);
	}

	public List<Map<String, Object>> selectCommentCountList(String articleIds) {
		return sqlSession.selectList(NAME_SPACE + "selectCommentCountList", articleIds);
	}
	
	public void insertArticleRank(List<ArticleRank> articleRankList) {
		sqlSession.insert(NAME_SPACE + "insertArticleRank", articleRankList);
	}
	
	public void deleteArticleRank() {
		sqlSession.delete(NAME_SPACE + "deleteArticleRank");
	}
	
}
