package com.kkk26kkk.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.BoardDAO;
import com.kkk26kkk.bbs.model.BoardVO;

@Service
public class BoardService {
	@Autowired
	BoardDAO boardDAO;

	public List<BoardVO> getBoardList(int page) {
		return boardDAO.getBoardList(page);
	}
	
	public int getBoardListCount() {
		return boardDAO.getBoardListCount();
	}
	
	public BoardVO getBoard(int idx) {
		return boardDAO.getBoard(idx);
	}
	
	public void insertBoard(BoardVO board) {
		boardDAO.insertBoard(board);
	}

	public void deleteBoard(int idx) {
		boardDAO.deleteBoard(idx);
	}

	public String getBoardPw(int idx) {
		return boardDAO.getBoardPw(idx);
	}

	public void updateBoard(BoardVO board) {
		boardDAO.updateBoard(board);
	}

	public void replyBoard(BoardVO board) {
		boardDAO.replyBoard(board);
	}
	
}
