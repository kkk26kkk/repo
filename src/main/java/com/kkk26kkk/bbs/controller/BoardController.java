package com.kkk26kkk.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kkk26kkk.bbs.model.BoardVO;
import com.kkk26kkk.bbs.model.UserVO;
import com.kkk26kkk.bbs.service.BoardService;
import com.kkk26kkk.bbs.service.UserService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;
	
	/** REST **/
//	@RequestMapping(value = "/board", method = RequestMethod.GET)
//	@ResponseBody
//	Map<String, Object> getBoardList() {
//		Map<String, Object> result = new HashMap<>();
//
//		List<BoardVO> boardList = boardService.getBoardList();
//		
//		result.put("result", Boolean.TRUE);
//		result.put("data", boardList);
//		
//		return result;
//	}	
//	@RequestMapping(value = "/board/{idx}", method = RequestMethod.GET)
//	@ResponseBody
//	Map<String, Object> getBoard(@PathVariable int idx) {
//		Map<String, Object> result = new HashMap<>();
//
//		BoardVO board = boardService.getBoard(idx);
//		
//		result.put("result", Boolean.TRUE);
//		result.put("data", board);
//		
//		return result;
//	}
//	@RequestMapping(value = "/board", method = RequestMethod.POST, headers = "Content-Type=application/json")
//	@ResponseBody
//	Map<String, Object> insertBoard(@RequestBody BoardVO board) {
//		Map<String, Object> result = new HashMap<>();
//
//		boardService.insertBoard(board);
//			
//		result.put("result", Boolean.TRUE);
//		
//		return result;
//	}
//	@RequestMapping(value = "/board", method = RequestMethod.DELETE, headers = "Content-Type=application/json")
//	@ResponseBody
//	Map<String, Object> deleteBoard(@RequestBody BoardVO board) {
//		Map<String, Object> result = new HashMap<>();
//
//		String boardPw = boardService.getBoardPw(board.getIdx());
//		
//		if(boardPw.equals(board.getPw())) {
//			boardService.deleteBoard(board.getIdx());
//			result.put("result", "삭제 성공");
//		} else {
//			result.put("result", "삭제 실패");
//		}
//		
//		return result;
//	}
//	@RequestMapping(value = "/board", method = RequestMethod.PUT, headers = "Content-Type=application/json")
//	@ResponseBody
//	Map<String, Object> updateBoard(@RequestBody BoardVO board) {
//		Map<String, Object> result = new HashMap<>();
//		
//		String boardPw = boardService.getBoardPw(board.getIdx());
//		
//		if(boardPw.equals(board.getPw())) {
//			boardService.updateBoard(board);
//			result.put("result", "수정 성공");
//		} else {
//			result.put("result", "수정 실패");
//		}
//		
//		return result;
//	}
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	String getBoardList(Model model) {
		
		List<BoardVO> boardList = boardService.getBoardList();
		
		model.addAttribute("boardList", boardList);
		
		return "/board/board_list";
	}
	
	@RequestMapping(value = "/board/{idx}", method = RequestMethod.GET)
	String getBoard(@PathVariable int idx, Model model) {
		BoardVO board = boardService.getBoard(idx);
		
		model.addAttribute("board", board);
		
		return "/board/board_view";
	}
		
//	@RequestMapping(value = "/board", method = RequestMethod.DELETE, headers = "Content-Type=application/json")
//	String deleteBoard(@RequestBody BoardVO board) {
//		String boardPw = boardService.getBoardPw(board.getIdx());
//		
//		if(boardPw.equals(board.getPw())) {
//			boardService.deleteBoard(board.getIdx());
//		} 
//		
//		return "board";
//	}
	
	@RequestMapping(value = "/board/board_delete") 
	String boardDelete(@RequestParam int idx, Model model) {
		boardService.deleteBoard(idx);
		
		return "redirect:../board";
	}
	
	@RequestMapping(value = "/board/board_edit") 
	String boardEdit(@RequestParam int idx, Model model) {
		BoardVO board = boardService.getBoard(idx);
		
		model.addAttribute("board", board);
		
		return "/board/board_edit";
	}
	
	@RequestMapping(value = "/board/board_edit_ok", method = RequestMethod.POST)
	String boardEditOk(BoardVO board) {
		boardService.updateBoard(board);
		
		return "redirect:../board";
	}
	
	@RequestMapping(value = "/board/board_write")
	String boardWrite() {
		return "/board/board_write";
	}
	
	@RequestMapping(value = "/board/board_write_ok")
	String boardWriteOk(BoardVO board, HttpServletRequest request) {
		UserVO user = userService.getUserInfo(request.getSession().getAttribute("userId").toString());
		
		board.setUserId(user.getId());
		board.setUserName(user.getName());
		board.setPw(user.getPw());
		boardService.insertBoard(board);
		
		return "redirect:../board";
	}
	
}
