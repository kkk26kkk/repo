package com.kkk26kkk.bbs.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.UserFollowVo;

@Repository
public class UserFollowDao {
	private static final String NAME_SPACE = "com.kkk26kkk.bbs.UserFollow.dao.";
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int insetUserFollow(UserFollowVo userFollowVo) {
		return sqlSession.insert("insertUserFollow", userFollowVo);
	}
	
	public List<String> selectFolloweeIds(String userId) {
		return sqlSession.selectList(NAME_SPACE + "selectFolloweeIds", userId);
	}
}
