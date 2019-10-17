package com.kkk26kkk.batch.model;

public class ArticleRankVo {
	private String articleId;
	private int commentCountRank;
	private int readCountRank;
	private int popularityRank;
	private int bestRank;
	private int risingRank;
	
	protected String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	protected int getCommentCountRank() {
		return commentCountRank;
	}
	public void setCommentCountRank(int commentCountRank) {
		this.commentCountRank = commentCountRank;
	}
	protected int getReadCountRank() {
		return readCountRank;
	}
	public void setReadCountRank(int readCountRank) {
		this.readCountRank = readCountRank;
	}
	protected int getPopularityRank() {
		return popularityRank;
	}
	public void setPopularityRank(int popularityRank) {
		this.popularityRank = popularityRank;
	}
	protected int getBestRank() {
		return bestRank;
	}
	public void setBestRank(int bestRank) {
		this.bestRank = bestRank;
	}
	protected int getRisingRank() {
		return risingRank;
	}
	public void setRisingRank(int risingRank) {
		this.risingRank = risingRank;
	}
	
}
