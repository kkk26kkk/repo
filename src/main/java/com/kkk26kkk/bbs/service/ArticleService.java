package com.kkk26kkk.bbs.service;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.ArticleDao;
import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleDto;
import com.kkk26kkk.bbs.model.ArticleVo;
import com.kkk26kkk.bbs.model.User;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	public Article getArticle(int articleId) {
		return articleDao.getArticle(articleId);
	}
	
	public ArticleDto getArticleDto(int articleId) {
		Article article = getArticle(articleId);
		
		ArticleDto articleDto = new ArticleDto();
    	articleDto.setArticleId(article.getArticleId());
    	articleDto.setUserName(article.getUserName());
    	articleDto.setTitle(article.getTitle());
    	articleDto.setContents(article.getContents());
		
		return articleDto;
	}

	public void insertArticle(ArticleDto articleDto, User user) throws SQLException {
		ArticleVo articleVo = new ArticleVo();
		articleVo.setUserId(user.getUserId());
		articleVo.setUserName(user.getUserName());
		articleVo.setTitle(articleDto.getTitle());
		articleVo.setContents(articleDto.getContents());
		
		int resultCnt = articleDao.insertArticle(articleVo);
		if(1 != resultCnt) {
			throw new SQLException("게시글 등록을 실패 했습니다.");
		}
		
		if(StringUtils.isNotEmpty(articleDto.getNoticeFlag())) {
			int articleId = getCurrentArticleId();
			resultCnt = insertNoticeArticle(articleId);
		}
		
		if(1 != resultCnt) {
			throw new SQLException("공지글 등록을 실패 했습니다.");
		}
	}
	
	private int getCurrentArticleId() {
		return articleDao.getCurrentArticleId();
	}
	
	private int insertNoticeArticle(int articleId) throws SQLException {
		return articleDao.insertNoticeArticle(articleId);
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
}
