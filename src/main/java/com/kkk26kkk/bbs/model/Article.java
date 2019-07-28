package com.kkk26kkk.bbs.model;

import org.apache.commons.lang3.time.FastDateFormat;

import com.kkk26kkk.common.model.Path;

public class Article extends ArticleVo {
	private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
	
	public ArticleDto showContents() {
		ArticleDto dto = new ArticleDto(getUserName(), getTitle(), getContents(), String.valueOf(getReadCount()));
		dto.setRegDtm(fdf.format(getRegDtm()));
		dto.setLink(Path.Article.getPath() + "/" + super.getArticleId());
		return dto;
	}
	
	public ArticleDto showArticle() {
		ArticleDto dto = new ArticleDto(getUserName(), getTitle(), getContents(), String.valueOf(getReadCount()));
		dto.setArticleId(getArticleId());
		dto.setUserId(getUserId());
		dto.setRegDtm(fdf.format(getRegDtm()));
		dto.setUpdateFormLink(Path.UpdateForm.getPath() + "?articleId=" + getArticleId());
		dto.setReplyFormLink(Path.ReplyForm.getPath() + "?articleId=" + getArticleId());
		dto.setDeleteLink(Path.Delete.getPath());
		dto.setCommentLink(Path.Comment.getPath());
		return dto;
	}
}
