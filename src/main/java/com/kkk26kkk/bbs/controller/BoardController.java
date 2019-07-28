package com.kkk26kkk.bbs.controller;

import java.util.List;
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
import com.kkk26kkk.common.model.Page;
import com.kkk26kkk.common.model.Path;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
//	@Autowired
//	private Environment environment;

	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	String showBoard(Model model, @RequestParam(value = "page", defaultValue = "1", required = false) int page) {
	    int pageSize = 10; // 한 화면에 출력할 레코드 갯수

	    int totalCount = boardService.getArticleCount(); // 총 리스트 수를 받아옵니다.

	    // 총 페이지 수
	    int totalPage = (totalCount + pageSize - 1) / pageSize;

	    // 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21, ...)
	    int startPage = ((page - 1) / 10) * 10 + 1;

	    // 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30, ...)
	    int endPage = startPage + 10 - 1;

	    if (endPage > totalPage)
	      endPage = totalPage;
	    
	    Page p = new Page();
	    p.setStartNum((page - 1) * 10 + 1);
	    p.setEndNum(page * 10);
		
		List<Article> articleList = boardService.getArticleList(p);
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
		List<ArticleDto> boardContents = articleList.stream()
				.map(Article::showContents)
//				.filter(v -> null == v.getContents())
//				.limit(10)
				.collect(Collectors.toList());
		
		model.addAttribute("page", page);
		model.addAttribute("maxPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("boardContents", boardContents);
		model.addAttribute("writeFormLink", Path.WriteForm.getPath());
		
		return "/board/articleList";
	}
	
}
