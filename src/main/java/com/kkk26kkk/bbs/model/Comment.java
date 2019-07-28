package com.kkk26kkk.bbs.model;

import org.apache.commons.lang3.time.FastDateFormat;

public class Comment extends CommentVo {
	private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
	
	public CommentDto showComment() {
		CommentDto dto = new CommentDto();
		dto.setCommentId(getCommentId());
		dto.setArticleId(getArticleId());
		dto.setUserName(getUserName());
		dto.setContents(getContents());
		dto.setRegDtm(fdf.format(getRegDtm()));
		
		return dto;
	}
}
