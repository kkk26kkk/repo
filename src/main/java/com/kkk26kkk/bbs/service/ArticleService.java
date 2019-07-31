package com.kkk26kkk.bbs.service;

import java.sql.SQLException;

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
		
		// TODO 전처리 필요하면 여기에
		int resultCnt = articleDao.insertArticle(articleVo);
		if(1 != resultCnt) {
			throw new SQLException("게시글 등록을 실패 했습니다.");
		}
	}
	
	public void updateArticle(Article article) {
		articleDao.updateArticle(article);
	}

	public void deleteArticle(int articleId) {
		articleDao.deleteArticle(articleId);
	}
}
