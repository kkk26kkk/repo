package com.kkk26kkk.bbs.article.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.article.model.Article;
import com.kkk26kkk.bbs.article.model.ArticleReadCountVo;
import com.kkk26kkk.bbs.article.model.ArticleVo;
import com.kkk26kkk.common.aop.AddComments;

@Repository
public class ArticleDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String NAME_SPACE = "com.kkk26kkk.bbs.article.dao.ArticleDao.";
	
	@AddComments(useMore = true, useTotal = true)
	public Article selectArticle(String articleId) {
		return sqlSession.selectOne(NAME_SPACE + "selectArticle", articleId);
	}
	
	public int insertArticle(ArticleVo articleVo) {
		return sqlSession.insert(NAME_SPACE + "insertArticle", articleVo);
	}

	public int updateArticle(ArticleVo articleVo) {
		return sqlSession.update(NAME_SPACE + "updateArticle", articleVo);
	}

	public int deleteArticle(String articleId) {
		return sqlSession.delete(NAME_SPACE + "deleteArticle", articleId);
	}

	public String selectArticleSeqNextVal() {
		return sqlSession.selectOne(NAME_SPACE + "selectArticleSeqNextVal");
	}

	public int insertNoticeArticle(String articleId) {
		return sqlSession.insert(NAME_SPACE + "insertNoticeArticle", articleId);
	}

	public int insertReadCount(ArticleReadCountVo articleReadCountVo) {
		return sqlSession.insert(NAME_SPACE + "insertReadCount", articleReadCountVo);
	}

}
