package com.kkk26kkk.bbs.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.User;
import com.kkk26kkk.bbs.model.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSessionTemplate sqlSession; 

	public User getUser(String id) {
		return sqlSession.selectOne("getUser", id);
	}

	public int insertUser(UserVo userVo) {
		return sqlSession.insert("insertUser", userVo);
	}

}
