package com.kkk26kkk.bbs.model;

public abstract class ArticleDecorator implements Article {
	protected Article article;
	
	ArticleDecorator(Article article) {
		this.article = article;
	}
	
	@Override
	public abstract ArticleDto showHeader();
	@Override
	public abstract ArticleDto showContent();
	@Override
	public abstract String getArticleId();
}
