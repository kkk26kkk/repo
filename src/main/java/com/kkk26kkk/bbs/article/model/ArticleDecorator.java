package com.kkk26kkk.bbs.article.model;

public abstract class ArticleDecorator extends Article {
	protected Article article;
	
	ArticleDecorator(Article article) {
		this.article = article;
	}
	
	@Override
	public final String getRootId() {
		return article.getRootId();
	}

	// XXX RootArticleDecorator로 type 변경 후 왜 superset method(Article클래스)로 호출하지 못하는 것일까?
	@Override
	public final String getArticleId() {
		return article.getArticleId();
	}

	@Override
	public final ArticleDto showHeader() {
		return article.showHeader();
	}
	
	@Override
	public abstract ArticleDto showContent();
}
