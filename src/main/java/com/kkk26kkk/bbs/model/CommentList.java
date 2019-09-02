package com.kkk26kkk.bbs.model;

import java.util.List;
import java.util.stream.Collectors;

import com.kkk26kkk.common.model.PageList;

public class CommentList extends ArticleDecorator {
	private PageList<Comment> commentList;
	
	public CommentList(Article article) {
		super(article);
	}
	
	public CommentList(Article article, PageList<Comment> commentList) {
		super(article);
		this.commentList = commentList;
	}
	
	@Override
	public ArticleDto showHeader() {
		return article.showHeader();
	}
	@Override
	public ArticleDto showContent() {
		ArticleDto dto = article.showContent();
		
		if(null != commentList) {
			List<CommentDto> list = commentList.getList().stream()
					.map(Comment::showContent)
					.collect(Collectors.toList());
			PageList<CommentDto> commentDtoList = new PageList<CommentDto>(list, commentList.getPage(), commentList.getPageSize(), commentList.getTotalCount(), commentList.hasNext());
			dto.setCommentList(commentDtoList);
		}
		
		return dto;
	}
	@Override
	public String getArticleId() {
		return article.getArticleId();
	}
}
