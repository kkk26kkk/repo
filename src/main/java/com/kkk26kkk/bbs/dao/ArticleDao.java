package com.kkk26kkk.bbs.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleRank;
import com.kkk26kkk.bbs.model.ArticleReadCountVo;
import com.kkk26kkk.bbs.model.ArticleVo;

@Repository
public class ArticleDao {
	private static final String NAME_SPACE = "Article";
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public Article getArticle(String articleId) {
		return sqlSession.selectOne("getArticle", articleId);
	}
	
	public int insertArticle(ArticleVo articleVo) {
		return sqlSession.insert("insertArticle", articleVo);
	}

	public int updateArticle(ArticleVo articleVo) {
		return sqlSession.update("updateArticle", articleVo);
	}

	public int deleteArticle(String articleId) {
		return sqlSession.delete("deleteArticle", articleId);
	}

	public String getArticleSeqNextVal() {
		return sqlSession.selectOne("getArticleSeqNextVal");
	}

	public int insertNoticeArticle(String articleId) {
		return sqlSession.insert("insertNoticeArticle", articleId);
	}

	public int insertReadCount(ArticleReadCountVo articleReadCountVo) {
		return sqlSession.insert("insertReadCount", articleReadCountVo);
	}

	public List<Article> selectArticleList() {
		return sqlSession.selectList("selectArticleList");
	}

	public Map<String, Map<String, Object>> getReadCountList() {
		return sqlSession.selectMap("getReadCountList", "articleId");
	}

	public Map<String, Map<String, Object>> getCommentCountList() {
		return sqlSession.selectMap("getCommentCountList", "articleId");
	}
	
	public void insertArticleRank(List<ArticleRank> articleRankVoList) {
		for(ArticleRank articleRank : articleRankVoList) {
			sqlSession.insert("insertArticleRank", articleRank);
		}
	}
	
	public void deleteArticleRank() {
		sqlSession.delete("deleteArticleRank");
	}
	
}
