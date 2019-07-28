package com.kkk26kkk.bbs.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.common.model.Page;

@Repository
public class BoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public List<Article> getArticleList(Page page) {
		return sqlSession.selectList("getArticleList", page);
	}
	
	public int getArticleCount() {
		return sqlSession.selectOne("getArticleCount");
	}
	
}
