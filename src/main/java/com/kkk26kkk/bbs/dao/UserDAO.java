package com.kkk26kkk.bbs.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.UserVO;

@Repository
public class UserDAO {
	@Autowired
	private SqlSessionTemplate sqlSession; 
	
	public String getUserPw(String id) {
		return sqlSession.selectOne("getUserPw", id);
	}

	public UserVO getUserInfo(String id) {
		return sqlSession.selectOne("getUserInfo", id);
	}

}
