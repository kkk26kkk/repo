package com.kkk26kkk.bbs.model;

import org.apache.commons.lang3.time.FastDateFormat;

import com.kkk26kkk.common.model.Path;

public class XArticle extends ArticleVo implements Article {
	private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
	
	@Override
	public ArticleDto showHeader() {
		ArticleDto dto = new ArticleDto(getArticleId(), getUserName(), getTitle());
		dto.setReadCount(String.valueOf(getReadCount()));
		dto.setRegDtm(fdf.format(getRegDtm()));
		dto.setLink(Path.Board.getPath() + "/" + super.getArticleId());
		return dto;
	}
	@Override
	public ArticleDto showContent() {
		ArticleDto dto = new ArticleDto(getArticleId(), getUserName(), getTitle(), getContents());
		dto.setReadCount(String.valueOf(getReadCount()));
		dto.setArticleId(getArticleId());
		dto.setUserId(getUserId());
		dto.setRegDtm(fdf.format(getRegDtm()));
		return dto;
	}
	@Override
	public String getArticleId() {
		return super.getArticleId();
	}
}
