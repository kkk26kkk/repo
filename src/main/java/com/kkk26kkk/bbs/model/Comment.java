package com.kkk26kkk.bbs.model;

import org.apache.commons.lang3.time.FastDateFormat;

public class Comment extends CommentVo {
	private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
	
	public CommentDto showContent() {
		CommentDto dto = new CommentDto();
		dto.setCommentId(getCommentId());
		dto.setArticleId(getArticleId());
		dto.setUserName(getUserName());
		dto.setContents(getContents());
		dto.setRegDtm(fdf.format(getRegDtm()));
		
		return dto;
	}

	@Override
	public String getCode() {
		if(true) {
			// 비즈니스 요구사항이 바뀌어서, 특정 케이스가 추가되었을 때
			// 실제로 사용하는 필드인, 오버라이드한 메소드만 호출 추적해보면 영향범위 파악이 끝남
		}
		return super.getCode();
	}
	
	@Override
	public String getUserId() {
		return super.getUserId();
	}
}