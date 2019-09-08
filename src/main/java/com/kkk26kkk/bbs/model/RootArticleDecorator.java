package com.kkk26kkk.bbs.model;

public class RootArticleDecorator extends ArticleDecorator {
	private Article rootArticle;
	
	public RootArticleDecorator(Article article, Article parentArticle) {
		super(article);
		this.rootArticle = parentArticle;
	}

	@Override
	public ArticleDto showContent() {
		ArticleDto dto = article.showContent();
		if(null == rootArticle) {
			return dto;
		}
		
		dto.setRootArticle(rootArticle.showContent());
		
		return dto;
	}
}
