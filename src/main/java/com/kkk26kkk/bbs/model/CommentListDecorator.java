package com.kkk26kkk.bbs.model;

import java.util.List;
import java.util.stream.Collectors;

import com.kkk26kkk.common.model.PageList;

public class CommentListDecorator extends ArticleDecorator {
	private PageList<Comment> commentList;
	
	public CommentListDecorator(Article article, PageList<Comment> commentList) {
		super(article);
		this.commentList = commentList;
	}
	
	@Override
	public ArticleDto showContent() {
		ArticleDto dto = article.showContent();
		if(null == commentList) {
			return dto;
		}
		
		List<CommentDto> list = commentList.getList().stream()
				.map(Comment::showContent)
				.collect(Collectors.toList());
		PageList<CommentDto> commentDtoList = new PageList<CommentDto>(list, commentList.getPage(), commentList.getPageSize(), commentList.getTotalCount(), commentList.hasNext());
		dto.setCommentList(commentDtoList);
		
		return dto;
	}
}
