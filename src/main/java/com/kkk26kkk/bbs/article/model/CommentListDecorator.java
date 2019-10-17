package com.kkk26kkk.bbs.article.model;

import java.util.List;
import java.util.stream.Collectors;

import com.kkk26kkk.bbs.comment.model.Comment;
import com.kkk26kkk.bbs.comment.model.CommentDto;
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
		dto.setComments(list);
		dto.setcPage(commentList.getPage());
		dto.setcTotalPage(commentList.getTotalPage());
		dto.setcHasNext(commentList.hasNext());
		
		return dto;
	}
}
