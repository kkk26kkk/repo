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
	
	protected <E> PageList<E> selectPageListMore(String statement, BaseParam baseParam) {
		return selectPageList(statement, null, baseParam);
	}
	
	protected <E> PageList<E> selectPageListTotal(String statement, String statementTotalCount, BaseParam baseParam) {
		return selectPageList(statement, statementTotalCount, baseParam);
	}
	
	private <E> PageList<E> selectPageList(String statement, String statementTotalCount, BaseParam baseParam) {
		
		List<E> list = sqlSession.selectList(statement, baseParam);
		
		int totalCount = 0;
		boolean hasNext = false;
		
		if(null == list) {
			return new PageList<E>(Collections.emptyList(), baseParam.getPage(), baseParam.getPageSize(), totalCount, hasNext);
		}
		
		if(baseParam.useTotal() && null != statementTotalCount) {
			totalCount = sqlSession.selectOne(statementTotalCount);
		}
		
		if(baseParam.useMore() && baseParam.getPageSize() < list.size()) {
			hasNext = true;
			list = list.subList(0, baseParam.getPageSize());
		}
		
		return new PageList<E>(list, baseParam.getPage(), baseParam.getPageSize(), totalCount, hasNext);
	}
	
	protected <E> Map<String, PageList<E>> selectPageListMapMore(String statement, BaseParam baseParam, Function<E, String> groupId) {
		return selectPageListMap(statement, null, baseParam, groupId);
	}
	
	protected <E> Map<String, PageList<E>> selectPageListMapTotal(String statement, String statementTotalCount, BaseParam baseParam, Function<E, String> groupId) {
		return selectPageListMap(statement, statementTotalCount, baseParam, groupId);
	}
	
	private <E> Map<String, PageList<E>> selectPageListMap(String statement, String statementTotalCount, BaseParam baseParam, Function<E, String> groupId) {
		
		List<E> list = sqlSession.selectList(statement, baseParam);
		
		Map<String, PageList<E>> pageListMap = new HashMap<>();
		Map<String, List<E>> listMap = new HashMap<>();
		Map<String, Integer> totalCountMap = new HashMap<>();
		
		if(null == list) {
			pageListMap.put(null, new PageList<E>(Collections.emptyList(), baseParam.getPage(), baseParam.getPageSize(), 0, false));
			return pageListMap;
		}
		
		if(baseParam.useTotal() && null != statementTotalCount) {
			List<Map<String, Object>> totalCountMapList = sqlSession.selectList(statementTotalCount);
			totalCountMap = totalCountMapList.stream()
					.collect(Collectors.toMap(tc -> (String)tc.get("id"), tc -> (Integer)tc.get("totalCount")));
		}
		
		listMap = list.stream()
			.collect(Collectors.groupingBy(groupId));

        for(Entry<String, List<E>> entry : listMap.entrySet()) {
            List<E> listTemp = entry.getValue();
			boolean hasNext = false;
			
			if(baseParam.useMore() && baseParam.getPageSize() < listTemp.size()) {
				hasNext = true;
				listTemp = listTemp.subList(0, baseParam.getPageSize());
			}
			
			int totalCount = 0;
			Integer totalCountValue = totalCountMap.get(entry.getKey());
			if(baseParam.useTotal() && null != totalCountValue) {
				totalCount = totalCountValue;
			}
			
			pageListMap.put(entry.getKey(), new PageList<E>(listTemp, baseParam.getPage(), baseParam.getPageSize(), totalCount, hasNext));
        }
		
		return pageListMap;
	}
}
