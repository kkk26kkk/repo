package com.kkk26kkk.user.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.user.model.User;
import com.kkk26kkk.user.model.UserFollowVo;
import com.kkk26kkk.user.model.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String NAME_SPACE = "com.kkk26kkk.user.dao.UserDao.";

	public User selectUser(String id) {
		return sqlSession.selectOne(NAME_SPACE + "selectUser", id);
	}

	public int insertUser(UserVo userVo) {
		return sqlSession.insert(NAME_SPACE + "insertUser", userVo);
	}

	public int insetUserFollow(UserFollowVo userFollowVo) {
		return sqlSession.insert(NAME_SPACE + "insertUserFollow", userFollowVo);
	}
	
	public List<String> selectFolloweeIds(String userId) {
		return sqlSession.selectList(NAME_SPACE + "selectFolloweeIds", userId);
	}
}
