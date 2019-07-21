package com.kkk26kkk.bbs.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleVo;

@Repository
public class BoardDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public List<Article> getBoardList(int page) {
		return sqlSession.selectList("getBoardList", page);
	}
	
	public int getBoardListCount() {
		return sqlSession.selectOne("getBoardListCount");
	}
	
	public ArticleVo getBoard(int idx) {
		return sqlSession.selectOne("getBoard", idx);
	}
	
	public void insertBoard(ArticleVo board) {
		sqlSession.insert("insertBoard", board);
	}

	public void deleteBoard(int idx) {
		sqlSession.delete("deleteBoard", idx);
	}

	public String getBoardPw(int idx) {
		return sqlSession.selectOne("getBoardPw", idx);
	}

	public void updateBoard(ArticleVo board) {
		sqlSession.update("updateBoard", board);
	}

	public void replyBoard(ArticleVo board) {
		sqlSession.insert("replyBoard", board);
	}

}
