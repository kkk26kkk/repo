package com.kkk26kkk.bbs.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class test11 {
	public static void main(String[] args) {
		String str = ",";

		List<String> articleIdList = new ArrayList<String>();
		
		articleIdList.add("1");
		articleIdList.add("2");
		articleIdList.add("3");
		articleIdList.add("4");
		articleIdList.add("5");
		
		String articleIdListStr = StringUtils.join(articleIdList, str);
		System.out.println(articleIdListStr);
	}
}
