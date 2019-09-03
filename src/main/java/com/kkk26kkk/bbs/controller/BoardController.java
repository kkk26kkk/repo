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
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleDto;
import com.kkk26kkk.bbs.model.ArticleParam;
import com.kkk26kkk.bbs.model.User;
import com.kkk26kkk.bbs.service.BoardService;
import com.kkk26kkk.common.model.PageList;
import com.kkk26kkk.common.model.Path;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
//	@Autowired
//	private Environment environment;
	
	private static final int pageSize = 10;

	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	String showBoard(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String sort) {
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

		ArticleParam articleParam = new ArticleParam
				.Builder(pageSize)
				.useTotal(true)
				.useMore(true)
				.sort(sort)
				.build();
		
	    PageList<Article> pageList = boardService.getArticleList(articleParam);
	    articleParam = null;
	    
		List<Article> articleList = pageList.getList();
		int totalPage = pageList.getTotalPage();
		int totalCount = pageList.getTotalCount();
		boolean hasNext = pageList.hasNext();
		
		List<ArticleDto> boardContents = articleList.stream()
				.map(Article::showHeader)
//				.filter(v -> null == v.getContents())
//				.limit(10)
				.collect(Collectors.toList());

		model.addAttribute("loginFormLink", Path.LoginForm.getPath());
		model.addAttribute("signUpFormLink", Path.SignUpForm.getPath());
		model.addAttribute("writeFormLink", Path.WriteForm.getPath());
		model.addAttribute("logoutLink", Path.Logout.getPath());
		model.addAttribute("boardContents", boardContents);
		model.addAttribute("page", page);
		
		model.addAttribute("hasNext", hasNext);
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalCount", totalCount);
		
		return "/board/articleList";
	}
	
	@RequestMapping(value = "/board/showMore", method = RequestMethod.GET)
	@ResponseBody
	Map<String, Object> showMore(@RequestParam int page, @RequestParam String sort) {
		Map<String, Object> map = new HashMap<>();
		
		ArticleParam articleParam = new ArticleParam
				.Builder(pageSize)
				.build();
		
		List<Article> articleList = boardService.getArticleListMore(articleParam);
		List<ArticleDto> boardContents = articleList.stream()
				.map(Article::showHeader)
				.collect(Collectors.toList());
		
		map.put("boardContents", boardContents);
		map.put("page", page);
		
		return map;
	}

	@RequestMapping(value = "/board/feedList", method = RequestMethod.GET)
	@ResponseBody Map<String, Object> feedList(Model model, @RequestParam(defaultValue = "0") int page, User user) {
		Map<String, Object> map = new HashMap<>();
		
		ArticleParam articleParam = new ArticleParam
				.Builder(pageSize)
				.useMore(true)
				.build();
		
		PageList<Article> pageArticleList = boardService.getFeedList(articleParam); 
		
		List<Article> articleList = pageArticleList.getList();
		int totalPage = pageArticleList.getTotalPage();
		int totalCount = pageArticleList.getTotalCount();
		boolean hasNext = pageArticleList.hasNext();
		
		List<ArticleDto> articleDtoList = articleList.stream()
			.map(Article::showContent)
			.collect(Collectors.toList());
		
		map.put("articleList", articleDtoList);
		map.put("totalPage", totalPage);
		map.put("totalCount", totalCount);
		map.put("hasNext", hasNext);
		
		return map;
	}
	
	@RequestMapping(value = "/board/clipboard", method = RequestMethod.GET)
	@ResponseBody Map<String, Object> clipboard(@RequestParam(defaultValue = "0") int page, User user) {
		Map<String, Object> map = new HashMap<>();
		
		String articleIdList = boardService.getArticleIdList(user.getUserId());
		
		ArticleParam articleParam = new ArticleParam
				.Builder(pageSize)
				.useMore(true)
				.articleIdList(articleIdList)
				.build();
		
		PageList<Article> pageArticleList = boardService.getClipboardList(articleParam);
		List<Article> articleList = pageArticleList.getList();
//		int totalPage = pageArticleList.getTotalPage();
//		int totalCount = pageArticleList.getTotalCount();
//		boolean hasNext = pageArticleList.hasNext();
		
		List<ArticleDto> articleDtoList = articleList.stream()
				.map(Article::showContent)
				.collect(Collectors.toList());
			
		map.put("articleList", articleDtoList);
//		map.put("totalPage", totalPage);
//		map.put("totalCount", totalCount);
//		map.put("hasNext", hasNext);
		
		return map;
	}
}
