package com.kkk26kkk.bbs.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

public class User extends UserVo {
	
	// TODO followeeIdList 또는 followeeIds 필드 추가 - 로그인 시점 or 변화되는 시점에 업데이트
	
	// TODO 위 추가한 필드에서 특정 userId가 있는지 체크하는 메소드
	
	public boolean isUserId(String userId) {
		if(StringUtils.equals(userId, this.getUserId())) {
			return true;
		}
		
		return false;
	}
	
	public boolean isLogin() {
		return StringUtils.isNotEmpty(super.getUserId());
	}
	
    public static String hash(String str) {
        String salt = "juhyung park @@@ 162 psy War !@";
        StringBuilder sb = new StringBuilder();

        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-1");
            sh.update(str.getBytes());
            sh.update(salt.getBytes());
            byte byteData[] = sh.digest();

            for(int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            return str;
        }
        
        return sb.toString();
    }
    
    public ArticleDto createArticle() {
    	ArticleDto dto = new ArticleDto();
    	dto.setUserId(this.getUserId());
    	dto.setUserName(this.getUserName());
    	
    	return dto;
    }

    @Override
	public String getUserId() {
		return super.getUserId();
	}

    @Override
	public String getUserName() {
		return super.getUserName();
	}
    
    @Override
    public String getUserPw() {
    	return super.getUserPw();
    }
    
}
