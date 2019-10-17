package com.kkk26kkk.user.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.user.dao.UserDao;
import com.kkk26kkk.user.model.User;
import com.kkk26kkk.user.model.UserDto;
import com.kkk26kkk.user.model.UserFollowVo;
import com.kkk26kkk.user.model.UserVo;

@Service	
public class UserService {
	@Autowired
	private UserDao userDao;

	public User getUser(String id) {
		return userDao.selectUser(id);
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

	public void insertUserFollow(String followUserId, String loginUserId) throws SQLException {
		UserFollowVo userFollowVo = new UserFollowVo();
		userFollowVo.setFolloweeId(followUserId);
		userFollowVo.setFollowerId(loginUserId);
		
		int resultCnt = userDao.insetUserFollow(userFollowVo);
		
		if(1 != resultCnt) {
			throw new SQLException("팔로우 추가에 실패 했습니다.");
		}
	}
	
	public List<String> getFolloweeIds(String userId) {
		return userDao.selectFolloweeIds(userId);
	}
}
