package com.kkk26kkk.user.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk26kkk.common.exception.BizException;
import com.kkk26kkk.user.dao.UserDao;
import com.kkk26kkk.user.model.User;
import com.kkk26kkk.user.model.UserDto;
import com.kkk26kkk.user.model.UserFollowVo;
import com.kkk26kkk.user.model.UserVo;

@Service	
public class UserService {
	@Autowired
	private UserDao userDao;
	
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

	public User getUser(UserDto userDto) throws BizException {
		User user = userDao.selectUser(userDto.getUserId());

		if(null == user) {
			throw new BizException("해당 유저가 존재하지 않습니다.");
		}
		
		if(StringUtils.equals(User.hash(userDto.getUserPw()), user.getUserPw())) {
			List<String> followeeIdList = userDao.selectFolloweeIds(user.getUserId());
			user.setFolloweeIds(StringUtils.join(followeeIdList, ","));
			
			return user;
		} else {
			throw new BizException("로그인 정보를 확인해주세요.");
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
}
