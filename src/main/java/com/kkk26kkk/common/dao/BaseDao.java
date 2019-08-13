package com.kkk26kkk.common.dao;

import java.util.Collections;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.kkk26kkk.common.model.BaseParam;
import com.kkk26kkk.common.model.PageList;

public class BaseDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public <E> PageList<E> getPageListMore(String statement, BaseParam pageListParam) {
		return getPageList(statement, null, pageListParam);
	}
	
	public <E> PageList<E> getPageListTotal(String statement, String statementTotalCount, BaseParam pageListParam) {
		return getPageList(statement, statementTotalCount, pageListParam);
	}
	
	private <E> PageList<E> getPageList(String statement, String statementTotalCount, BaseParam pageListParam) {
		
		List<E> list = sqlSession.selectList(statement, pageListParam);
		
		int totalCount = 0;
		boolean hasNext = false;
		
		if(null == list) {
			return new PageList<E>(Collections.emptyList(), pageListParam.getPage(), pageListParam.getPageSize(), totalCount, hasNext);
		}
		
		if(pageListParam.useTotal() && null != statementTotalCount) {
			
			totalCount = sqlSession.selectOne(statementTotalCount);
		}
		
		if(pageListParam.useMore() && pageListParam.getPageSize() < list.size()) {
			hasNext = true;
			list = list.subList(0, pageListParam.getPageSize());
		}
		
		return new PageList<E>(list, pageListParam.getPage(), pageListParam.getPageSize(), totalCount, hasNext);
	}
}
