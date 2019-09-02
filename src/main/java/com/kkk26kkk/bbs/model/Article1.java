package com.kkk26kkk.bbs.model;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.FastDateFormat;

import com.kkk26kkk.common.model.PageList;
import com.kkk26kkk.common.model.Path;

public class Article1 extends ArticleVo {
	private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
	
	private PageList<Comment> commentList;
//	ArticleRank articleRank;

	public PageList<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(PageList<Comment> commentList) {
		this.commentList = commentList;
	}

	public ArticleDto showHeader() {
		ArticleDto dto = new ArticleDto(getArticleId(), getUserName(), getTitle());
		dto.setReadCount(String.valueOf(getReadCount()));
		dto.setRegDtm(fdf.format(getRegDtm()));
		dto.setLink(Path.Board.getPath() + "/" + super.getArticleId());
		return dto;
	}
	
	public ArticleDto showContent() {
		ArticleDto dto = new ArticleDto(getArticleId(), getUserName(), getTitle(), getContents());
		dto.setReadCount(String.valueOf(getReadCount()));
		dto.setArticleId(getArticleId());
		dto.setUserId(getUserId());
		dto.setRegDtm(fdf.format(getRegDtm()));
		
		if(null != commentList) {
			List<CommentDto> commentDtoList = commentList.getList().stream()
					.map(Comment::showContent)
					.collect(Collectors.toList());
			
            // XXX ArticleDto에 PageList<CommentDto> 필드를 추가해야 할까요? 아니면 List<CommentDto>필드를 추가해야 할까요?
            // TODO PageList<CommentDto>, List<CommentDto> 둘 다 필요
			dto.setCommentList(commentDtoList);
		}
		
		return dto;
	}
	
	@Override
	public String getArticleId() {
		return super.getArticleId();
	}

//	public ArticleRank getArticleRank() {
//		if(null == articleRank) {
//			this.articleRank = new ArticleRank(super.getArticleId());
//		}
//		return articleRank;
//	}
//
//	public void setArticleRank(ArticleRank articleRank) {
//		this.articleRank = articleRank;
//	}
	
}
