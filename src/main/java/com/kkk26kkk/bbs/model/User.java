package com.kkk26kkk.bbs.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.kkk26kkk.common.model.Path;

public class User extends UserVo {
	
	public String getHashPw() {
		return this.hash(super.getUserPw());
	}
	
    public String hash(String str) {
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
    
    public ArticleDto writeArticle(User user) {
    	ArticleDto dto = new ArticleDto();
    	dto.setUserName(user.getUserName());
    	dto.setLink(Path.Write.getPath());
    	
    	return dto;
    }
    
    public ArticleDto updateArticle(Article article) {
    	ArticleDto dto = new ArticleDto();
    	dto.setArticleId(article.getArticleId());
    	dto.setUserName(article.getUserName());
    	dto.setTitle(article.getTitle());
    	dto.setContents(article.getContents());
    	dto.setLink(Path.Update.getPath());
    	return dto;
    }
    
    public ArticleDto replyArticle(Article article) {
    	ArticleDto dto = new ArticleDto();
    	dto.setArticleId(article.getArticleId());
    	dto.setUserName(article.getUserName());
    	dto.setTitle(article.getTitle());
    	dto.setLink(Path.Reply.getPath());
    	return dto;
    }
    
}
