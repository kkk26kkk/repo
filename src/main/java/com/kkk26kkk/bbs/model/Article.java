package com.kkk26kkk.bbs.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import com.kkk26kkk.common.model.Path;

public class Article extends ArticleVo {
	private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");

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
		return dto;
	}
	
	public boolean isArticleWriter(String userId) {
		if(StringUtils.equals(this.getUserId(), userId)) {
			return true;
		}
		
		return false;
	}
}
