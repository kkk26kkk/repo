package com.kkk26kkk.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.UserDAO;
import com.kkk26kkk.bbs.model.User;

@Service	
public class UserService {
	@Autowired
	private UserDAO userDAO;

	public User getUser(String id) {
		return userDAO.getUser(id);
	}
}
