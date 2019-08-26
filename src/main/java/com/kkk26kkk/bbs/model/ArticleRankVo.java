package com.kkk26kkk.bbs.model;

public class ArticleRankVo {
	private String articleId;
	private int commentCountRank;
	private int readCountRank;
	private int popularityRank;
	private int bestRank;
	private int risingRank;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public int getCommentCountRank() {
		return commentCountRank;
	}
	public void setCommentCountRank(int commentCountRank) {
		this.commentCountRank = commentCountRank;
	}
	public int getReadCountRank() {
		return readCountRank;
	}
	public void setReadCountRank(int readCountRank) {
		this.readCountRank = readCountRank;
	}
	public int getPopularityRank() {
		return popularityRank;
	}
	public void setPopularityRank(int popularityRank) {
		this.popularityRank = popularityRank;
	}
	public int getBestRank() {
		return bestRank;
	}
	public void setBestRank(int bestRank) {
		this.bestRank = bestRank;
	}
	public int getRisingRank() {
		return risingRank;
	}
	public void setRisingRank(int risingRank) {
		this.risingRank = risingRank;
	}
	
}
