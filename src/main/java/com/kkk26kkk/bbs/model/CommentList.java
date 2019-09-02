package com.kkk26kkk.bbs.model;

import java.util.List;
import java.util.stream.Collectors;

import com.kkk26kkk.common.model.PageList;

public class CommentList extends ArticleDecorator {
	private PageList<Comment> commentList;
	
	public PageList<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(PageList<Comment> commentList) {
		this.commentList = commentList;
	}
	
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
	
	public ArticleDto showContent() {
		ArticleDto dto = article.showContent();
		
		if(null != commentList) {
			List<CommentDto> commentDtoList = commentList.getList().stream()
					.map(Comment::showContent)
					.collect(Collectors.toList());
			dto.setCommentList(commentDtoList);
		}
		
		return dto;
	}

	// TODO 여기서 반환하는게 맞을까?
	@Override
	public String getArticleId() {
		return null;
	}
}
