package com.kkk26kkk.bbs.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.kkk26kkk.common.model.PageDto;
import com.kkk26kkk.common.model.PageList;

public class BaseDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public <E> PageList<E> getArticleList(String statement, int page, int pageSize, int totalCount) {
		PageDto pageDto = new PageDto
				.Builder(page, pageSize)
				.startNum(page, pageSize)
				.endNum(page, pageSize)
				.build();
		
		List<E> list = sqlSession.selectList(statement, pageDto);
		
		PageList<E> pageList = new PageList<E>(list, page, pageSize);
		pageList.setTotalCount(totalCount);
		
		return pageList;
	}
	
	public <E> PageList<E> getArticleListMore(String statement, int page, int pageSize) {
		
		return null;
	}
}
