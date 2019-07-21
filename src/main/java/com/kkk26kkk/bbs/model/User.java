package com.kkk26kkk.bbs.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User extends UserVO {
	
	public String getHashPw() {
		return this.hash(super.getPw());
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
}
