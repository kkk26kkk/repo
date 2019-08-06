package com.kkk26kkk.bbs.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.kkk26kkk.common.model.PageListParam;
import com.kkk26kkk.common.model.PageList;

public class BaseDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public <E> PageList<E> getPageListMore(String statement, PageListParam pageDto) {
		return getPageList(statement, null, pageDto);
	}
	
	public <E> PageList<E> getPageListTotal(String statement, String statementTotalCount, PageListParam pageDto) {
		return getPageList(statement, statementTotalCount, pageDto);
	}
	
	private <E> PageList<E> getPageList(String statement, String statementTotalCount, PageListParam pageDto) {
		
		List<E> list = sqlSession.selectList(statement, pageDto);
		
		int totalCount = 0;
		boolean hasNext = false;
		
		if(null == list) {
			return new PageList<E>(list, pageDto.getPage(), pageDto.getPageSize(), totalCount, hasNext);
		}
		
		if(pageDto.useTotal() && null != statementTotalCount) {
			totalCount = sqlSession.selectOne(statementTotalCount);
		}
		
		if(pageDto.useMore() && pageDto.getPageSize() < list.size()) {
			hasNext = true;
			list = list.subList(0, pageDto.getPageSize());
		}
		
		return new PageList<E>(list, pageDto.getPage(), pageDto.getPageSize(), totalCount, hasNext);
	}
}
