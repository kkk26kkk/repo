package com.kkk26kkk.bbs.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

import com.kkk26kkk.common.model.Path;

public class User extends UserVo {
	
//	private boolean login = false;
	
	public boolean isUserId(String userId) {
		if(StringUtils.equals(userId, this.getUserId())) {
			return true;
		}
		
		return false;
	}
	
	public boolean isLogin() {
		return StringUtils.isNotEmpty(super.getUserId());
	}
	
	public String getHashPw() {
		return User.hash(super.getUserPw());
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
//    	dto.setLink(Path.Write.getPath());
    	
    	return dto;
    }
    
}
