package com.kkk26kkk.bbs.article.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.article.model.Article;
import com.kkk26kkk.bbs.article.model.ArticleParam;
import com.kkk26kkk.common.aop.AddComments;
import com.kkk26kkk.common.aop.AddRootArticle;
import com.kkk26kkk.common.aop.CacheInSession;
import com.kkk26kkk.common.dao.BaseDao;
import com.kkk26kkk.common.model.PageList;

@Repository
public class BoardDao extends BaseDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String NAME_SPACE = "com.kkk26kkk.bbs.article.dao.BoardDao.";
	
	public PageList<Article> selectArticleList(ArticleParam articleParam) {
		return super.selectPageListTotal(NAME_SPACE + "selectArticleList", NAME_SPACE + "selectArticleCount", articleParam);
//		return super.selectPageListMore(NAME_SPACE + "getArticleList", pageListParam);
	}
	
	public int selectArticleCount() {
		return sqlSession.selectOne(NAME_SPACE + "selectArticleCount");
	}
	
	public List<Article> selectArticleListMore(ArticleParam articleParam) {
		return sqlSession.selectList(NAME_SPACE + "selectArticleList", articleParam);
	}
		
	@AddComments(useMore = true, useTotal = true)
	@AddRootArticle
	@CacheInSession(name = "BoardDao.selectFeedList", key = "userId,page,pageSize,sort", type = "com.kkk26kkk.bbs.article.model.ArticleParam")
	public PageList<Article> selectFeedList(ArticleParam articleParam) {
		return super.selectPageListMore(NAME_SPACE + "selectFeedList", articleParam);
//		return super.selectPageListTotal(NAME_SPACE + "selectFeedList", NAME_SPACE + "selectArticleCount", articleParam);
	}

	@AddComments(useMore = true, useTotal = true)
	@AddRootArticle
	@CacheInSession(name = "BoardDao.selectClipboardList", key = "userId,page,pageSize,sort", type = "com.kkk26kkk.bbs.article.model.ArticleParam")
	public PageList<Article> selectClipboardList(ArticleParam articleParam) {
		return super.selectPageListMore(NAME_SPACE + "selectClipboardList", articleParam);
//		return super.selectPageListTotal(NAME_SPACE + "selectClipboardList", NAME_SPACE + "selectArticleCount", articleParam);
	}

	public List<Article> selectParentArticleList(String rootIds) {
		return sqlSession.selectList(NAME_SPACE + "selectParentArticleList", rootIds);
	}
	
}