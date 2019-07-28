package com.kkk26kkk.bbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.Comment;
import com.kkk26kkk.bbs.model.CommentDto;
import com.kkk26kkk.bbs.model.User;
import com.kkk26kkk.bbs.service.ArticleService;
import com.kkk26kkk.bbs.service.CommentService;
import com.kkk26kkk.bbs.service.UserService;
import com.kkk26kkk.common.model.Path;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	
	// 글 상세보기
	@RequestMapping(value = "/board/{articleId}", method = RequestMethod.GET)
	String show(@PathVariable int articleId, Model model) {
		Article article = articleService.getArticle(articleId);
		
		model.addAttribute("article", article.showArticle());
		
		return "/board/show";
	}
	
	// 글 수정 폼
	@RequestMapping(value = "/board/update")
	String updateForm(@RequestParam int articleId, Model model) {
		Article article = articleService.getArticle(articleId);
		
		model.addAttribute("article", new User().updateArticle(article));
		
		return "/board/update";
	}
	
	// 글 수정 처리
	@RequestMapping(value = "/board", method = RequestMethod.PUT)
	@ResponseBody
	Map<String, Object> update(@RequestBody Article article) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			articleService.updateArticle(article);
			result.put("code", HttpStatus.OK);
			result.put("redirect", Path.Article.getPath() + "/" + article.getArticleId());
		} catch(Exception e) {
			result.put("msg", "해당 글이 존재하지 않습니다.");
            result.put("code", HttpStatus.NOT_FOUND);
            e.printStackTrace();
		}
		
		return result;
	}
	
	// 글 작성 폼
	@RequestMapping(value = "/board/write")
	String writeForm(HttpServletRequest request, Model model) {
		model.addAttribute("article", new User().writeArticle(userService.getUser(request.getSession().getAttribute("userId").toString())));
		
		return "/board/write";
	}
	
	// 글 답변 폼
	@RequestMapping(value = "/board/reply")
	String replyForm(@RequestParam int articleId, Model model, HttpServletRequest request) {
		String userName = request.getSession().getAttribute("userName").toString();
		
		Article article = articleService.getArticle(articleId);
		article.setUserName(userName);
		
		model.addAttribute("article", new User().replyArticle(article));
		
		return "/board/reply";
	}
	
	// 글 등록 처리
	@RequestMapping(value = "/board", method = RequestMethod.POST)
	@ResponseBody
	Map<String, Object> write(@RequestBody Article article, HttpServletRequest request) {
		String userId = request.getSession().getAttribute("userId").toString();
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		article.setUserId(userId);
		
		try {
			articleService.insertArticle(article);
			result.put("code", HttpStatus.OK);
			result.put("redirect", Path.ArticleList.getPath());
		} catch(Exception e) {
			result.put("msg", "해당 글이 존재하지 않습니다.");
            result.put("code", HttpStatus.NOT_FOUND);
            e.printStackTrace();
		}
		
		return result;
	}
	
	// 글 삭제 처리
	@RequestMapping(value = "/board", method = RequestMethod.DELETE)
	@ResponseBody
	Map<String, Object> remove(@RequestBody Article article) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			articleService.deleteArticle(article.getArticleId());
			result.put("code", HttpStatus.OK);
			result.put("redirect", Path.ArticleList.getPath());
		} catch(Exception e) {
			result.put("msg", "해당 글이 존재하지 않습니다.");
            result.put("code", HttpStatus.NOT_FOUND);
            e.printStackTrace();
		}
		
		return result;
	}
	
	// 댓글 등록
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	@ResponseBody
	Map<String, Object> comment(@RequestBody Comment comment, HttpServletRequest request) {
		comment.setUserId(request.getSession().getAttribute("userId").toString());
		comment.setUserName(request.getSession().getAttribute("userName").toString());
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			commentService.insertComment(comment);
			result.put("code", HttpStatus.OK);
			result.put("userName", comment.getUserName());
			result.put("contents", comment.getContents());
		} catch(Exception e) {
			result.put("msg", "해당 글이 존재하지 않습니다.");
            result.put("code", HttpStatus.NOT_FOUND);
            e.printStackTrace();
		}
		
		return result;
	}
	
	// 댓글 리스트
	@RequestMapping(value = "/comment", method = RequestMethod.GET)
	@ResponseBody
	Map<String, Object> getCommentList(@RequestParam int articleId, HttpServletRequest request) {
		List<Comment> commentList = commentService.getCommentList(articleId);
		
		List<CommentDto> articleComments = commentList.stream()
				.map(Comment::showComment)
				.collect(Collectors.toList());
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("articleComments", articleComments);
		
		return result;
	}
}
