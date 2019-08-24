package com.kkk26kkk.bbs.model;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import com.kkk26kkk.common.model.PageList;
import com.kkk26kkk.common.model.Path;

public class Article extends ArticleVo {
	private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
	
	private PageList<Comment> commentList;

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
			
			dto.setCommentList(commentDtoList);
		}
		// TODO 댓글 더보기를 위한 set 추가 필요
		// dto.setCommentPage()
		// dto.setCommentHasNext()
		
		return dto;
	}
	
	public boolean isArticleWriter(String userId) {
		if(StringUtils.equals(this.getUserId(), userId)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public String getArticleId() {
		return super.getArticleId();
	}
}
