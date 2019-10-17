package com.kkk26kkk.batch.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkk26kkk.batch.service.BatchService;

@Controller
public class BatchController {
	@Autowired
	private BatchService batchService;
	
	@RequestMapping(value = "/batch/rankingArticle")
	@ResponseBody Map<String, Object> rankingArticle() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		batchService.saveArticleRank(99999);
		
		return result;
	}
}
