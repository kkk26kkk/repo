package com.kkk26kkk.bbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleDto;
import com.kkk26kkk.bbs.service.BoardService;
import com.kkk26kkk.common.model.PageList;
import com.kkk26kkk.common.model.PageListParam;
import com.kkk26kkk.common.model.Path;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
//	@Autowired
//	private Environment environment;
	
	private static final int pageSize = 10;

	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	String showBoard(Model model, @RequestParam(defaultValue = "1") int page) {
//		int length = 0;
//		for(Article board : boardList) {
//			if(null == board.getContents()) {
//				continue;
//			}
//			board.showContents();
//			if(10 == ++length) {
//				break;
//			}
//		}

		PageListParam pageListParam = new PageListParam
				.Builder(page, pageSize)
				.useTotal(true)
				.useMore(true)
				.build();
		
	    PageList<Article> pageList = boardService.getArticleList(pageListParam);
	    pageListParam = null;
	    
	    Map<String, String> map = new HashMap<>();
	    // 뭐 담아서 썼다가
	    map = new HashMap<>();
	    // 다시 뭐 담아서 썼다가
	    
	    // TODO
	    Map<String, String> map2 = new HashMap<>();
	    // 뭐 담아서 썼다가
	    map2 = null; // 해당 객체에 들어있는 데이터를 안 쓰게 된 시점에 null 넣어준다
	    map2 = new HashMap<>();
	    // 다시 뭐 담아서 썼다가
	    
		List<Article> articleList = pageList.getList();
		int totalPage = pageList.getTotalPage();
		int totalCount = pageList.getTotalCount();
		boolean hasNext = pageList.hasNext();
		
		List<ArticleDto> boardContents = articleList.stream()
				.map(Article::showHeader)
//				.filter(v -> null == v.getContents())
//				.limit(10)
				.collect(Collectors.toList());

		model.addAttribute("writeFormLink", Path.WriteForm.getPath());
		model.addAttribute("boardContents", boardContents);
		model.addAttribute("page", page);
		
		model.addAttribute("hasNext", hasNext);
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalCount", totalCount);
		
		return "/board/articleList";
	}
	
}
