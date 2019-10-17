package com.kkk26kkk.bbs.article.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkk26kkk.bbs.article.model.Article;
import com.kkk26kkk.bbs.article.model.ArticleDto;
import com.kkk26kkk.bbs.article.model.ArticleParam;
import com.kkk26kkk.bbs.article.service.BoardService;
import com.kkk26kkk.common.code.Path;
import com.kkk26kkk.common.exception.BizException;
import com.kkk26kkk.common.model.PageList;
import com.kkk26kkk.user.model.User;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
//	@Autowired
//	private Environment environment;
	private static final int PAGE_SIZE = 10;

	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	String showArticles(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(required = false) String sort) {
		ArticleParam articleParam = new ArticleParam
				.Builder(PAGE_SIZE)
				.page(page)
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
	    int startPage = ((page - 1) / PAGE_SIZE) * PAGE_SIZE + 1;
	    int endPage = startPage + PAGE_SIZE - 1;
	    if(endPage > totalPage) {
	    	endPage = totalPage;
	    }
		
		List<ArticleDto> articleDtoList = articleList.stream()
				.map(Article::showHeader)
				.collect(Collectors.toList());

		model.addAttribute("articles", articleDtoList);
		model.addAttribute("page", page);
		model.addAttribute("hasNext", hasNext);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("loginFormLink", Path.Login.getPath());
		model.addAttribute("signUpFormLink", Path.SignUp.getPath());
		model.addAttribute("writeFormLink", Path.Write.getPath());
		model.addAttribute("logoutLink", Path.Logout.getPath());
		
		return "/articles/main";
	}
	
	@RequestMapping(value = "/showMore", method = RequestMethod.GET)
	@ResponseBody Map<String, Object> showArticlesMore(@RequestParam int page, @RequestParam(required = false) String sort) {
		Map<String, Object> map = new HashMap<>();
		
		ArticleParam articleParam = new ArticleParam
				.Builder(PAGE_SIZE)
				.page(page)
//				.useMore(true)
				.sort(sort)
				.build();
		
		List<Article> articleList = boardService.getArticleListMore(articleParam);
		List<ArticleDto> articleDtoList = articleList.stream()
				.map(Article::showHeader)
				.collect(Collectors.toList());
		
		map.put("articles", articleDtoList);
		map.put("page", page);
		
		return map;
	}

	@RequestMapping(value = "/feeds", method = RequestMethod.GET)
	@ResponseBody Map<String, Object> showFeeds(User user) {
		Map<String, Object> map = new HashMap<>();
		
		ArticleParam articleParam = new ArticleParam
				.Builder(PAGE_SIZE)
				.useMore(true)
				.userId(user.getFolloweeIds())
				.loginUserId(user.getUserId())
				.build();
		
		try {
			PageList<Article> pageList = boardService.getFeedList(articleParam); 
			List<Article> articleList = pageList.getList();
			int page = pageList.getPage();
			int totalPage = pageList.getTotalPage();
			boolean hasNext = pageList.hasNext();
			
			List<ArticleDto> articleDtoList = articleList.stream()
				.map(Article::showContent) // TODO environment, user를 기본적으로 받는 데이터컨버터(팩토리?) 추가
				.collect(Collectors.toList());
			
			map.put("articles", articleDtoList);
			map.put("page", page);
			map.put("totalPage", totalPage);
			map.put("hasNext", hasNext);
		} catch(BizException e) {
			map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping(value = "/clipboard/{userId}", method = RequestMethod.GET)
	@ResponseBody Map<String, Object> showClipboard(User user, @PathVariable String userId) {
		Map<String, Object> map = new HashMap<>();
		
		ArticleParam articleParam = new ArticleParam
				.Builder(PAGE_SIZE)
				.useMore(true)
				.userId(userId)
				.loginUserId(user.getUserId())
				.isFollowing(user.isFollowing(userId))
				.build();
		
		try {
			PageList<Article> pageList = boardService.getClipboardList(articleParam);
			List<Article> articleList = pageList.getList();
			int page = pageList.getPage();
			int totalPage = pageList.getTotalPage();
			boolean hasNext = pageList.hasNext();
			
			List<ArticleDto> articleDtoList = articleList.stream()
					.map(Article::showContent)
					.collect(Collectors.toList());
				
			map.put("articles", articleDtoList);
			map.put("page", page);
			map.put("totalPage", totalPage);
			map.put("hasNext", hasNext);
		} catch(BizException e) {
			map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
}
