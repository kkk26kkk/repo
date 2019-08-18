package com.kkk26kkk.bbs.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleParam;
import com.kkk26kkk.bbs.model.Comment;
import com.kkk26kkk.bbs.model.CommentParam;
import com.kkk26kkk.common.dao.BaseDao;
import com.kkk26kkk.common.model.PageList;

@Repository
public class BoardDao extends BaseDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public PageList<Article> getArticleList(ArticleParam articleParam) {
		return super.getPageListTotal("getArticleList", "getArticleCount", articleParam);
//		return super.getPageListMore("getArticleList", pageListParam);
	}
	
	public int getArticleCount() {
		return sqlSession.selectOne("getArticleCount");
	}
	
	public List<Article> getArticleListMore(ArticleParam articleParam) {
		return sqlSession.selectList("getArticleList", articleParam);
	}
	
	// TODO @AddComments
	public List<Article> getArticleListMoreAddComments(ArticleParam articleParam) {
		return sqlSession.selectList("getArticleList", articleParam);
	}

	public PageList<Article> getFeedArticleList(ArticleParam articleParam) {
		return super.getPageListTotal("getFeedArticleList", "getArticleCount", articleParam);
	}

	// TODO @AddComments
	public PageList<Article> getFeedArticleListAddComments(ArticleParam articleParam) {
		return super.getPageListTotal("getFeedArticleList", "getArticleCount", articleParam);
	}

	public PageList<Comment> getFeedCommentList(CommentParam commentParam) {
		return super.getPageListMore("getFeedCommentList", commentParam);
	}
	
}