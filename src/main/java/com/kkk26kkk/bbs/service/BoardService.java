package com.kkk26kkk.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.BoardDAO;
import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleVo;

@Service
public class BoardService {
	@Autowired
	BoardDAO boardDAO;

	public List<Article> getBoardList(int page) {
		return boardDAO.getBoardList(page);
	}
	
	public int getBoardListCount() {
		return boardDAO.getBoardListCount();
	}
	
	public ArticleVo getBoard(int idx) {
		return boardDAO.getBoard(idx);
	}
	
	public void insertBoard(ArticleVo board) {
		boardDAO.insertBoard(board);
	}

	public void deleteBoard(int idx) {
		boardDAO.deleteBoard(idx);
	}

	public String getBoardPw(int idx) {
		return boardDAO.getBoardPw(idx);
	}

	public void updateBoard(ArticleVo board) {
		boardDAO.updateBoard(board);
	}

	public void replyBoard(ArticleVo board) {
		boardDAO.replyBoard(board);
	}
	
}
