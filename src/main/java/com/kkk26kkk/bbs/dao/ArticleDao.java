package com.kkk26kkk.bbs.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleVo;

@Repository
public class ArticleDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public Article getArticle(int articleId) {
		return sqlSession.selectOne("getArticle", articleId);
	}
	
	public int insertArticle(ArticleVo articleVo) {
		return sqlSession.insert("insertArticle", articleVo);
	}

	public void updateArticle(Article article) {
		sqlSession.update("updateArticle", article);
	}

	public void deleteArticle(int articleId) {
		sqlSession.delete("deleteArticle", articleId);
	}
	
}
