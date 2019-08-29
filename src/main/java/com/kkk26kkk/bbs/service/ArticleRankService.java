package com.kkk26kkk.bbs.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.ArticleRankDao;
import com.kkk26kkk.bbs.model.ArticleRank;

@Service
public class ArticleRankService {
	@Autowired
	private ArticleRankDao articleRankDao;
	
	public void saveRanking() {
		List<ArticleRank> articleRankList = articleRankDao.selectArticleIdListForRank();
		Map<String, Map<String, Object>> readCountMap = articleRankDao.selectReadCountList();
		Map<String, Map<String, Object>> commentCountMap = articleRankDao.selectCommentCountList();
		
		articleRankList.stream()
			.peek(articleRank -> {
				Map<String, Object> map = readCountMap.get(articleRank.getArticleId());
				if(null != map) {
					int readCountRank = (int) map.get("count");
					articleRank.setReadCountRank(readCountRank);
				}
			})
			.peek(articleRank -> {
				Map<String, Object> map = commentCountMap.get(articleRank.getArticleId());
				if(null != map) {
					int commentCountRank = (int) map.get("count");
					articleRank.setCommentCountRank(commentCountRank);
				}
			})
			.peek(articleRank -> {
				int readCountPoint = articleRank.getReadCountRank() * 2;
				int commentCountPoint = articleRank.getCommentCountRank() * 3;
				articleRank.setPopularityRank(readCountPoint + commentCountPoint);
			})
			.collect(Collectors.toList());
		
		articleRankDao.deleteArticleRank();
		articleRankDao.insertArticleRank(articleRankList);
	}
}
