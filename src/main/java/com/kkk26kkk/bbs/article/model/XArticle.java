package com.kkk26kkk.bbs.article.model;

import org.apache.commons.lang3.time.FastDateFormat;

public class XArticle extends Article {
	private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
	
	@Override
	public ArticleDto showContent() {
		ArticleDto dto = new ArticleDto(getArticleId(), getUserName(), getTitle(), getContents());
		dto.setReadCount(String.valueOf(getReadCount()));
		dto.setArticleId(getArticleId());
		dto.setUserId(getUserId());
		dto.setRegDtm(fdf.format(getRegDtm()));
		return dto;
	}
}
