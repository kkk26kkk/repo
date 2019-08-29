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
	
	public List<ArticleRank> selectArticleIdListForRank() {
		return sqlSession.selectList(NAME_SPACE + "selectArticleIdListForRank");
	}
	
	public Map<String, Map<String, Object>> selectReadCountList() {
		return sqlSession.selectMap(NAME_SPACE + "selectReadCountList", "articleId");
	}

	public Map<String, Map<String, Object>> selectCommentCountList() {
		return sqlSession.selectMap(NAME_SPACE + "selectCommentCountList", "articleId");
	}
	
	public void insertArticleRank(List<ArticleRank> articleRankList) {
		// TODO batch 처리
		for(ArticleRank articleRank : articleRankList) {
			sqlSession.insert(NAME_SPACE + "insertArticleRank", articleRank);
		}
	}
	
	public void deleteArticleRank() {
		sqlSession.delete(NAME_SPACE + "deleteArticleRank");
	}
	
}
