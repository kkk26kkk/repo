package com.kkk26kkk.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.UserDao;
import com.kkk26kkk.bbs.model.User;

@Service	
public class UserService {
	@Autowired
	private UserDao userDao;

	public User getUser(String id) {
		return userDao.getUser(id);
	}
}
