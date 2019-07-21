package com.kkk26kkk.bbs.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import com.kkk26kkk.common.model.Path;

public class Article extends ArticleVo {
	private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyyMMdd");
	
	public ArticleDto showContents() {
		ArticleDto dto = new ArticleDto(getUserName(), getTitle(), getContents(), String.valueOf(getReadCount()));
		dto.setRegDtm(fdf.format(getRegDtm()));
		dto.setLink(Path.Article.getPath() + "?idx=" + super.getIdx());
		return dto;
	}
}
