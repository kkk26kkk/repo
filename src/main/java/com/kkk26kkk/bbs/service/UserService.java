package com.kkk26kkk.bbs.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.bbs.dao.UserDao;
import com.kkk26kkk.bbs.model.User;
import com.kkk26kkk.bbs.model.UserDto;
import com.kkk26kkk.bbs.model.UserVo;

@Service	
public class UserService {
	@Autowired
	private UserDao userDao;

	public User getUser(String id) {
		return userDao.getUser(id);
	}

	public void insertUser(UserDto userDto) throws SQLException {
		UserVo userVo = new UserVo();
		userVo.setUserId(userDto.getUserId());
		userVo.setUserPw(User.hash(userDto.getUserPw()));
		userVo.setUserName(userDto.getUserName());
		
		int resultCnt = userDao.insertUser(userVo);
		
		if(1 != resultCnt) {
			throw new SQLException("유저 등록을 실패 했습니다.");
		}
	}

}
