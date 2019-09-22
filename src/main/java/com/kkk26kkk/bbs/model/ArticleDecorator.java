package com.kkk26kkk.bbs.model;

public abstract class ArticleDecorator extends Article {
	protected Article article;
	
	ArticleDecorator(Article article) {
		this.article = article;
	}
	
	@Override
	public abstract ArticleDto showContent();
}
