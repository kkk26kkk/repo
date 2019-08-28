package com.kkk26kkk.bbs.model;

public class ArticleRank extends ArticleRankVo {
	ArticleRank() {}
	ArticleRank(String articleId) {
		super.setArticleId(articleId);
	}
	public int getCommentCountRank() {
		return super.getCommentCountRank();
	}
	public int getReadCountRank() {
		return super.getReadCountRank();
	}
}
