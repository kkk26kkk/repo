package com.kkk26kkk.bbs.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.ArticleDto;
import com.kkk26kkk.bbs.model.ArticleVo;
import com.kkk26kkk.bbs.model.UserVO;
import com.kkk26kkk.bbs.service.BoardService;
import com.kkk26kkk.bbs.service.UserService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;
	@Autowired
	private Environment environment;

	@RequestMapping(value = "/board_list", method = RequestMethod.GET)
	String getBoardList(Model model, @RequestParam(value = "page", defaultValue = "1", required = false) int page) {
	    int pageSize = 10; // 한 화면에 출력할 레코드 갯수

	    int totalCount = boardService.getBoardListCount(); // 총 리스트 수를 받아옵니다.

	    // 총 페이지 수
	    int totalPage = (totalCount + pageSize - 1) / pageSize;

	    // 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21, ...)
	    int startPage = ((page - 1) / 10) * 10 + 1;

	    // 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30, ...)
	    int endPage = startPage + 10 - 1;

	    if (endPage > totalPage)
	      endPage = totalPage;
		
		List<Article> boardList = boardService.getBoardList(page);
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
		List<ArticleDto> boardContentsList = boardList.stream()
				.map(Article::showContents)
//				.filter(v -> null == v.getContents())
//				.limit(10)
				.collect(Collectors.toList());
		
		model.addAttribute("page", page);
		model.addAttribute("maxpage", totalPage);
		model.addAttribute("startpage", startPage);
		model.addAttribute("endpage", endPage);
		model.addAttribute("listcount", totalCount);
		model.addAttribute("boardList", boardList);
		
		return "/board/board_list";
	}
	
	@RequestMapping(value = "/board_view", method = RequestMethod.GET)
	String getBoard(@RequestParam("idx") int idx, @RequestParam("page") int page,
			Model model) {
		ArticleVo board = boardService.getBoard(idx);
		
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		
		return "/board/board_view";
	}

	@RequestMapping(value = "/board_delete") 
	String boardDelete(@RequestParam int idx, Model model) {
		boardService.deleteBoard(idx);
		
		return "redirect:/board_list";
	}
	
	@RequestMapping(value = "/board_edit") 
	String boardEdit(@RequestParam int idx, @RequestParam("page") int page,
			Model model) {
		ArticleVo board = boardService.getBoard(idx);
		
		model.addAttribute("board", board);
		
		return "/board/board_edit";
	}
	
	@RequestMapping(value = "/board_edit_ok", method = RequestMethod.POST)
	String boardEditOk(ArticleVo board) {
		boardService.updateBoard(board);
		
		return "redirect:/board_list";
	}
	
	@RequestMapping(value = "/board_write")
	String boardWrite() {
		return "/board/board_write";
	}
	
	@RequestMapping(value = "/board_write_ok")
	String boardWriteOk(ArticleVo board, HttpServletRequest request) {
		UserVO user = userService.getUserInfo(request.getSession().getAttribute("userId").toString());
		
		board.setUserId(user.getId());
		board.setUserName(user.getName());
		board.setPw(user.getPw());
		boardService.insertBoard(board);
		
		return "redirect:/board_list";
	}
	
	@RequestMapping(value = "/board_reply")
	String boardReply(@RequestParam int idx, @RequestParam("page") int page,
			Model model) {
		ArticleVo board = boardService.getBoard(idx);
		
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		
		return "/board/board_reply";
	}
	
	@RequestMapping(value = "/board_reply_ok")
	String boardReplyOk(ArticleVo board, @RequestParam("page") int page,
			HttpServletRequest request) {
		UserVO user = userService.getUserInfo(request.getSession().getAttribute("userId").toString());
		
		board.setUserId(user.getId());
		board.setUserName(user.getName());
		board.setPw(user.getPw());
		board.setGroupNo(board.getGroupNo());
		board.setGroupOrder(board.getGroupOrder() + 1);
		board.setGroupLayer(board.getGroupLayer() + 1);
		
		boardService.replyBoard(board);
		
		return "redirect:/board_list?page=" + page;
	}
}
