package com.kkk26kkk.bbs.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.ArticleDao;
import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleDto;
import com.kkk26kkk.bbs.model.ArticleVo;
import com.kkk26kkk.bbs.model.User;
import com.kkk26kkk.common.exception.BizException;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	public Article getArticle(int articleId) {
		return articleDao.getArticle(articleId);
	}

	public void insertArticle(ArticleDto articleDto, User user) throws SQLException {
		ArticleVo articleVo = new ArticleVo();
		articleVo.setUserId(user.getUserId());
		articleVo.setUserName(user.getUserName());
		articleVo.setTitle(articleDto.getTitle());
		articleVo.setContents(articleDto.getContents());
		
		int articleId = articleDao.getSeqNextVal();
		articleVo.setArticleId(articleId);
		
		int resultCnt = articleDao.insertArticle(articleVo);
		if(1 != resultCnt) {
			throw new SQLException("게시글 등록을 실패 했습니다.");
		}

		if(articleDto.isNotice()) {
			this.insertNoticeArticle(articleId);
		}
	}
	
	private void insertNoticeArticle(int articleId) throws SQLException {
		int resultCnt = articleDao.insertNoticeArticle(articleId);
		
		if(1 != resultCnt) {
			throw new SQLException("공지글 등록을 실패 했습니다.");
		}
	}
	
	public void updateArticle(int articleId, ArticleDto articleDto) throws SQLException {
		ArticleVo articleVo = new ArticleVo();
		articleVo.setArticleId(articleId);
		articleVo.setTitle(articleDto.getTitle());
		articleVo.setContents(articleDto.getContents());
		
		int resultCnt = articleDao.updateArticle(articleVo);
		if(1 != resultCnt) {
			throw new SQLException("게시글 수정을 실패 했습니다.");
		}
	}

	public void deleteArticle(int articleId) throws SQLException {
		int resultCnt = articleDao.deleteArticle(articleId);
		if(1 != resultCnt) {
			throw new SQLException("게시글 삭제를 실패했습니다.");
		}
	}
	
	public void updateArticleExample(int articleId, ArticleDto articleDto) throws BizException {
		ArticleVo articleVo = new ArticleVo();
		articleVo.setArticleId(articleId);
		articleVo.setTitle(articleDto.getTitle());
		articleVo.setContents(articleDto.getContents());
		
		int resultCnt = articleDao.updateArticle(articleVo);
		if(1 != resultCnt) {
			throw new BizException("게시글 수정을 실패 했습니다.");
		}
	}
}
