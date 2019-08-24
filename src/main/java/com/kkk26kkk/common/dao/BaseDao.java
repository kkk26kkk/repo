package com.kkk26kkk.common.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

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
	
	public <E> Map<String, PageList<E>> getPageListMore(String statement, BaseParam pageListParam, Function<E, String> groupId) {
		return getPageList(statement, null, pageListParam, groupId);
	}
	
	public <E> Map<String, PageList<E>> getPageListTotal(String statement, String statementTotalCount, BaseParam pageListParam, Function<E, String> groupId) {
		return getPageList(statement, statementTotalCount, pageListParam, groupId);
	}
	
	private <E> Map<String, PageList<E>> getPageList(String statement, String statementTotalCount, BaseParam pageListParam, Function<E, String> groupId) {
		
		List<E> list = sqlSession.selectList(statement, pageListParam);
		
		Map<String, PageList<E>> pageListMap = new HashMap<>();
		Map<String, List<E>> listMap = new HashMap<>();
		int totalCount = 0;
		boolean hasNext = false;
		
		if(null == list) {
			pageListMap.put(null, new PageList<E>(Collections.emptyList(), pageListParam.getPage(), pageListParam.getPageSize(), totalCount, hasNext));
			return pageListMap;
		}
		
		// TODO 구현
//		if(pageListParam.useTotal() && null != statementTotalCount) {
//			
//			totalCount = sqlSession.selectOne(statementTotalCount);
//		}
		
		listMap = list.stream()
			.collect(Collectors.groupingBy(groupId));

        for(Entry<String, List<E>> entry : listMap.entrySet()) {
            List<E> listTemp = entry.getValue();
			boolean hasNextTemp = false;
			
			if(pageListParam.useMore() && pageListParam.getPageSize() < listTemp.size()) {
				hasNextTemp = true;
				listTemp = listTemp.subList(0, pageListParam.getPageSize());
			}
			
			pageListMap.put(entry.getKey(), new PageList<E>(listTemp, pageListParam.getPage(), pageListParam.getPageSize(), totalCount, hasNextTemp));
        }
		
		return pageListMap;
	}
}
