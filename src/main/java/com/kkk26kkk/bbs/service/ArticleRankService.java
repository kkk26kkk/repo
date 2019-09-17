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
	
	public void saveRanking(int limit) {
		List<ArticleRank> articleRankList = articleRankDao.selectArticleIdListForRank(limit);
		String articleIds = articleRankList.stream()
				.map(ArticleRank::getArticleId)
				.collect(Collectors.joining(","));
		List<Map<String, Object>> readCountList = articleRankDao.selectReadCountList(articleIds);
		List<Map<String, Object>> commentCountList = articleRankDao.selectCommentCountList(articleIds);
		Map<String, Integer> readCountMap = readCountList.stream()
				.collect(Collectors.toMap(readCount -> (String)readCount.get("articleId"), readCount -> (Integer)readCount.get("count")));
		Map<String, Integer> commentCountMap = commentCountList.stream()
				.collect(Collectors.toMap(readCount -> (String)readCount.get("articleId"), readCount -> (Integer)readCount.get("count")));

		for(int i = 0 ; i < articleRankList.size(); i++) {
			ArticleRank articleRank = articleRankList.get(i);
			
			Integer readCountRank = readCountMap.get(articleRank.getArticleId());
			Integer commentCountRank = commentCountMap.get(articleRank.getArticleId());
			
			final int readCount = null != readCountRank ? readCountRank : 0;
			final int commentCount = null != commentCountRank ? commentCountRank : 0;
			final int Popularity = readCount * 2 + commentCount * 3;
			
			articleRank.setReadCountRank(readCount);
			articleRank.setCommentCountRank(commentCount);
			articleRank.setPopularityRank(Popularity);
		}
		
		articleRankDao.deleteArticleRank();
		articleRankDao.insertArticleRank(articleRankList);
	}
}
