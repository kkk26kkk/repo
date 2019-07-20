package com.kkk26kkk.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.UserDAO;
import com.kkk26kkk.bbs.model.UserVO;

@Service	
public class UserService {
	@Autowired
	private UserDAO userDAO;
	
	public String getUserPw(String id) {
		return userDAO.getUserPw(id);
	}

	public UserVO getUserInfo(String id) {
		return userDAO.getUserInfo(id);
	}
}
