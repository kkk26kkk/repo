package com.kkk26kkk.bbs.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.BoardVO;

@Repository
public class BoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public List<BoardVO> getBoardList() {
		return sqlSession.selectList("getBoardList");
	}
	
	public BoardVO getBoard(int idx) {
		return sqlSession.selectOne("getBoard", idx);
	}
	
	public void insertBoard(BoardVO board) {
		sqlSession.insert("insertBoard", board);
	}

	public void deleteBoard(int idx) {
		sqlSession.delete("deleteBoard", idx);
	}

	public String getBoardPw(int idx) {
		return sqlSession.selectOne("getBoardPw", idx);
	}

	public void updateBoard(BoardVO board) {
		sqlSession.update("updateBoard", board);
	}

}
