package com.kkk26kkk.bbs.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleParam;
import com.kkk26kkk.common.aop.AddComments;
import com.kkk26kkk.common.aop.AddRootArticle;
import com.kkk26kkk.common.aop.CacheInSession;
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
	
	@AddComments
	public List<Article> getArticleListMoreAddComments(ArticleParam articleParam) {
		return sqlSession.selectList("getArticleList", articleParam);
	}

	@AddComments
	public PageList<Article> getFeedArticleListAddCommentsTotal(ArticleParam articleParam) {
		return super.getPageListTotal("getFeedArticleList", "getArticleCount", articleParam);
	}
	
	@AddComments
	@AddRootArticle
	@CacheInSession(name = "BoardDao.getFeedArticleListAddCommentsMore", key = "userId,page,pageSize,sort", type = "com.kkk26kkk.bbs.model.ArticleParam")
	public PageList<Article> getFeedArticleListAddCommentsMore(ArticleParam articleParam) {
		return super.getPageListMore("getFeedArticleList", articleParam);
	}

	@AddComments
	@AddRootArticle
	@CacheInSession(name = "BoardDao.getClipboardListAddCommentsMore", key = "userId,page,pageSize,sort", type = "com.kkk26kkk.bbs.model.ArticleParam")
	public PageList<Article> getClipboardListAddCommentsMore(ArticleParam articleParam) {
		return super.getPageListMore("getClipboardList", articleParam);
	}
	
}