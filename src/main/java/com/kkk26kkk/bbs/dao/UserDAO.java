package com.kkk26kkk.bbs.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.User;

@Repository
public class UserDAO {
	@Autowired
	private SqlSessionTemplate sqlSession; 

	public User getUser(String id) {
		return sqlSession.selectOne("getUser", id);
	}

}
