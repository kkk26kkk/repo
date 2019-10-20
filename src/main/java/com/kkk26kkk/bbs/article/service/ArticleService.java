package com.kkk26kkk.bbs.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.article.dao.ArticleDao;
import com.kkk26kkk.bbs.article.model.Article;
import com.kkk26kkk.bbs.article.model.ArticleDto;
import com.kkk26kkk.bbs.article.model.ArticleReadCountVo;
import com.kkk26kkk.bbs.article.model.ArticleVo;
import com.kkk26kkk.common.exception.BizException;
import com.kkk26kkk.user.model.User;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	public Article getArticle(String articleId) {
		return articleDao.selectArticle(articleId);
	}

	public void insertArticle(ArticleDto articleDto, User user) throws BizException {
		ArticleVo articleVo = new ArticleVo();
		articleVo.setUserId(user.getUserId());
		articleVo.setUserName(user.getUserName());
		articleVo.setTitle(articleDto.getTitle());
		articleVo.setContents(articleDto.getContents());
		articleVo.setParentId(articleDto.getParentId());
		articleVo.setGrade(articleDto.getGrade());
		
		String articleId = articleDao.selectArticleSeqNextVal();
		articleVo.setArticleId(articleId);
		
		int resultCnt = articleDao.insertArticle(articleVo);
		if(1 != resultCnt) {
			throw new BizException("게시글 등록을 실패 했습니다.");
		}

		if(articleDto.isNotice()) {
			this.insertNoticeArticle(articleId);
		}
	}
	
	private void insertNoticeArticle(String articleId) throws BizException {
		int resultCnt = articleDao.insertNoticeArticle(articleId);
		
		if(1 != resultCnt) {
			throw new BizException("공지글 등록을 실패 했습니다.");
		}
	}
	
	public void updateArticle(String articleId, ArticleDto articleDto) throws BizException {
		ArticleVo articleVo = new ArticleVo();
		articleVo.setArticleId(articleId);
		articleVo.setTitle(articleDto.getTitle());
		articleVo.setContents(articleDto.getContents());
		
		int resultCnt = articleDao.updateArticle(articleVo);
		if(1 != resultCnt) {
			throw new BizException("게시글 수정을 실패 했습니다.");
		}
	}

	public void deleteArticle(String articleId) throws BizException {
		int resultCnt = articleDao.deleteArticle(articleId);
		if(1 != resultCnt) {
			throw new BizException("게시글 삭제를 실패했습니다.");
		}
	}
	
	public void insertReadCount(String articleId, String userId) throws BizException {
		ArticleReadCountVo articleReadCountVo = new ArticleReadCountVo();
		articleReadCountVo.setArticleId(articleId);
		articleReadCountVo.setUserId(userId);
		
		int resultCnt = articleDao.insertReadCount(articleReadCountVo);
		if(1 != resultCnt) {
			throw new BizException("조회수 증가 처리를 실패 했습니다.");
		}
	}
	
}