package com.kkk26kkk.bbs.model;

public abstract class ArticleDecorator extends Article {
	protected Article article;
	
	ArticleDecorator(Article article) {
		this.article = article;
	}
	
	// TODO 슈퍼셋에 있는 메소드들(데코레이팅 대상이 아닌) 여기에서 지울 수 있는 구조 알아보자

	@Override
	public final String getRootId() {
		return article.getRootId();
	}

	@Override
	public final void setRootId(String rootId) {
		article.setRootId(rootId);
	}

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
