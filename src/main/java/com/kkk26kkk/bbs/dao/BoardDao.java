package com.kkk26kkk.bbs.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.common.dao.BaseDao;
import com.kkk26kkk.common.model.PageList;
import com.kkk26kkk.common.model.PageListParam;

@Repository
public class BoardDao extends BaseDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public PageList<Article> getArticleList(PageListParam pageListParam) {
		return super.getPageListTotal("getArticleList", "getArticleCount", pageListParam);
//		return super.getPageListMore("getArticleList", pageListParam);
	}
	
	public int getArticleCount() {
		return sqlSession.selectOne("getArticleCount");
	}
	
	public List<Article> getArticleListMore(PageListParam pageListParam) {
		return sqlSession.selectList("getArticleList", pageListParam);
	}
	
}