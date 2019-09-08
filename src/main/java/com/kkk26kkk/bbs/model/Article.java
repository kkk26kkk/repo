package com.kkk26kkk.bbs.model;

import org.apache.commons.lang3.time.FastDateFormat;

import com.kkk26kkk.common.model.Path;

public abstract class Article extends ArticleVo {
	private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
	
	private String rootId;
	
	public String getRootId() {
		return rootId;
	}
	public void setRootId(String rootId) {
		this.rootId = rootId;
	}

	@Override
	public String getArticleId() {
		return super.getArticleId();
	}
	
	public ArticleDto showHeader() {
		ArticleDto dto = new ArticleDto(getArticleId(), getUserName(), getTitle());
		dto.setReadCount(String.valueOf(getReadCount()));
		dto.setRegDtm(fdf.format(getRegDtm()));
		dto.setLink(Path.Board.getPath() + "/" + super.getArticleId());
		return dto;
	}
	
	public abstract ArticleDto showContent();
}
