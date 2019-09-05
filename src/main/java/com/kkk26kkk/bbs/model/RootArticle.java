package com.kkk26kkk.bbs.model;

public class RootArticle extends ArticleDecorator {
	private Article rootArticle;
	
	public RootArticle(Article article) {
		super(article);
	}
	
	public RootArticle(Article article, Article parentArticle) {
		super(article);
		this.rootArticle = parentArticle;
	}

	@Override
	public ArticleDto showHeader() {
		return article.showHeader();
	}

	@Override
	public ArticleDto showContent() {
		ArticleDto dto = article.showContent();
		
		if(null != rootArticle) {
			dto.setRootArticle(rootArticle.showContent());
		}
		
		return dto;
	}

	@Override
	public String getArticleId() {
		return article.getArticleId();
	}
}
