package com.kkk26kkk.bbs.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.ArticleDao;
import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleDto;
import com.kkk26kkk.bbs.model.ArticleRank;
import com.kkk26kkk.bbs.model.ArticleReadCountVo;
import com.kkk26kkk.bbs.model.ArticleVo;
import com.kkk26kkk.bbs.model.User;
import com.kkk26kkk.common.exception.BizException;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	public Article getArticle(String articleId) {
		return articleDao.getArticle(articleId);
	}

	public void insertArticle(ArticleDto articleDto, User user) throws SQLException {
		ArticleVo articleVo = new ArticleVo();
		articleVo.setUserId(user.getUserId());
		articleVo.setUserName(user.getUserName());
		articleVo.setTitle(articleDto.getTitle());
		articleVo.setContents(articleDto.getContents());
		articleVo.setParentId(articleDto.getParentId());
		
		String articleId = articleDao.getArticleSeqNextVal();
		articleVo.setArticleId(articleId);
		
		int resultCnt = articleDao.insertArticle(articleVo);
		if(1 != resultCnt) {
			throw new SQLException("게시글 등록을 실패 했습니다.");
		}

		if(articleDto.isNotice()) {
			this.insertNoticeArticle(articleId);
		}
	}
	
	private void insertNoticeArticle(String articleId) throws SQLException {
		int resultCnt = articleDao.insertNoticeArticle(articleId);
		
		if(1 != resultCnt) {
			throw new SQLException("공지글 등록을 실패 했습니다.");
		}
	}
	
	public void updateArticle(String articleId, ArticleDto articleDto) throws SQLException {
		ArticleVo articleVo = new ArticleVo();
		articleVo.setArticleId(articleId);
		articleVo.setTitle(articleDto.getTitle());
		articleVo.setContents(articleDto.getContents());
		
		int resultCnt = articleDao.updateArticle(articleVo);
		if(1 != resultCnt) {
			throw new SQLException("게시글 수정을 실패 했습니다.");
		}
	}

	public void deleteArticle(String articleId) throws SQLException {
		int resultCnt = articleDao.deleteArticle(articleId);
		if(1 != resultCnt) {
			throw new SQLException("게시글 삭제를 실패했습니다.");
		}
	}
	
	public void updateArticleExample(String articleId, ArticleDto articleDto) throws BizException {
		ArticleVo articleVo = new ArticleVo();
		articleVo.setArticleId(articleId);
		articleVo.setTitle(articleDto.getTitle());
		articleVo.setContents(articleDto.getContents());
		
		int resultCnt = articleDao.updateArticle(articleVo);
		if(1 != resultCnt) {
			throw new BizException("게시글 수정을 실패 했습니다.");
		}
	}

	public void insertReadCount(String articleId, String userId) throws SQLException {
		ArticleReadCountVo articleReadCountVo = new ArticleReadCountVo();
		articleReadCountVo.setArticleId(articleId);
		articleReadCountVo.setUserId(userId);
		
		int resultCnt = articleDao.insertReadCount(articleReadCountVo);
		if(1 != resultCnt) {
			throw new SQLException("조회수 증가 처리를 실패 했습니다.");
		}
	}

	public void saveRanking() {
		List<Article> articleList = articleDao.selectArticleList();
		Map<String, Map<String, Object>> readCountMap = articleDao.getReadCountList();
		Map<String, Map<String, Object>> commentCountMap = articleDao.getCommentCountList();
		
		List<ArticleRank> articleRankList = articleList.stream()
			.peek(article -> {
				Map<String, Object> temp = readCountMap.get(article.getArticleId());
				if(null != temp) {
					BigDecimal bd = (BigDecimal)temp.get("count");
					int readCountRank = bd.intValue();
					article.getArticleRank().setReadCountRank(readCountRank);
				}
			})
			.peek(article -> {
				Map<String, Object> temp = commentCountMap.get(article.getArticleId());
				if(null != temp) {
					BigDecimal bd = (BigDecimal)temp.get("count");
					int commentCountRank = bd.intValue();
					article.getArticleRank().setCommentCountRank(commentCountRank);
				}
			})
			.peek(article -> {
				int readCountPoint = article.getArticleRank().getReadCountRank() * 2;
				int commentCountPoint = article.getArticleRank().getCommentCountRank() * 3;
				article.getArticleRank().setPopularityRank(readCountPoint + commentCountPoint);
			})
			.map(Article::getArticleRank)
			.collect(Collectors.toList());
		
		articleDao.deleteArticleRank();
		articleDao.insertArticleRank(articleRankList);
	}
	
}