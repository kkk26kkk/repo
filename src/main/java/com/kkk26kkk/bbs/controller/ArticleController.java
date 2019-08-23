package com.kkk26kkk.bbs.controller;

import java.sql.SQLException;
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
import com.kkk26kkk.bbs.model.ArticleDto;
import com.kkk26kkk.bbs.model.Comment;
import com.kkk26kkk.bbs.model.CommentDto;
import com.kkk26kkk.bbs.model.CommentParam;
import com.kkk26kkk.bbs.model.User;
import com.kkk26kkk.bbs.service.ArticleService;
import com.kkk26kkk.bbs.service.CommentService;
import com.kkk26kkk.common.model.PageList;
import com.kkk26kkk.common.model.Path;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	
	private static final int pageSize = 10;
	
	// 글 상세보기
	@RequestMapping(value = "/board/{articleId}", method = RequestMethod.GET)
	String show(@PathVariable String articleId, Model model) {
		Article article = articleService.getArticle(articleId);
		
		model.addAttribute("article", article.showContent());
		
		model.addAttribute("updateFormLink", Path.UpdateForm.getRestPath(articleId));
		model.addAttribute("replyFormLink", Path.ReplyForm.getRestPath(articleId));
		model.addAttribute("deleteLink", Path.Board.getRestPath(articleId));
		model.addAttribute("commentLink", Path.Comment.getPath());
		model.addAttribute("commentListLink", Path.Comment.getRestPath(articleId));
		
		return "/board/show";
	}
	
	// 글 작성 폼
	// 커스텀 인터페이스로 에스펙트 - @LoginRequire - user에서 isLogin 체크
	@RequestMapping(value = {"/board/write", "/board/{articleId}/reply"})
	String writeForm(HttpServletRequest request, Model model, @PathVariable(required = false) String articleId, User user) {
		ArticleDto articleDto = user.createArticle();
		
		if(Path.ReplyForm.containedTo(request.getRequestURI())) {
			Article article = articleService.getArticle(articleId);
			ArticleDto parentArticleDto = article.showContent();
			
			articleDto.setArticleId(parentArticleDto.getArticleId());
			articleDto.setTitle("RE:" + parentArticleDto.getTitle());
		}
		
		model.addAttribute("article", articleDto);
//		articleDto = null;
		model.addAttribute("path", Path.Board.getPath());
		
		return "/board/write";
	}
	
	// 글 수정 폼
	@RequestMapping(value = "/board/{articleId}/update")
	String updateForm(@PathVariable String articleId, Model model) {
		Article article = articleService.getArticle(articleId);
		ArticleDto articleDto = article.showContent();
		
		model.addAttribute("article", articleDto);
		
		return "/board/update";
	}
	
	// 글 등록 처리
	@RequestMapping(value = "/board", method = RequestMethod.POST)
	@ResponseBody
	Map<String, Object> write(@RequestBody ArticleDto articleDto, User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			articleService.insertArticle(articleDto, user);
			result.put("code", HttpStatus.OK);
			result.put("redirect", Path.ArticleList.getPath());
		} catch(Exception e) {
			result.put("msg", e.getMessage());
            result.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
		}
		
		return result;
	}
	
	// 글 수정 처리
	@RequestMapping(value = "/board/{articleId}", method = RequestMethod.PUT)
	@ResponseBody
	Map<String, Object> update(@PathVariable String articleId, @RequestBody ArticleDto articleDto, User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(!user.isUserId(articleDto.getUserId())) {
			result.put("msg", "로그인 정보가 다릅니다.");
            result.put("code", HttpStatus.FORBIDDEN);
		}
		
		try {
			articleService.updateArticle(articleId, articleDto);
			result.put("code", HttpStatus.OK);
			result.put("redirect", Path.Board.getPath() + "/" + articleId);
		} catch(SQLException e) {
			result.put("msg", e.getMessage());
            result.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
		} catch(Exception e) {
			result.put("msg", "해당 글이 존재하지 않습니다.");
            result.put("code", HttpStatus.NOT_FOUND);
            e.printStackTrace();
		}
		
		return result;
	}
	
	// 글 삭제 처리
	@RequestMapping(value = "/board/{articleId}", method = RequestMethod.DELETE)
	@ResponseBody
	Map<String, Object> remove(@PathVariable String articleId, @RequestBody ArticleDto articleDto, User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(!user.isUserId(articleDto.getUserId())) {
			result.put("msg", "로그인 정보가 다릅니다.");
            result.put("code", HttpStatus.FORBIDDEN);
		}
		
		try {
			articleService.deleteArticle(articleId);
			result.put("code", HttpStatus.OK);
			result.put("redirect", Path.ArticleList.getPath());
		}/* catch(BizException e) { // 커스텀 예외 예시 -> updateArticleExample
			result.put("msg", e.getMessage());
            result.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
		}*/ catch(Exception e) {
			result.put("msg", "해당 글이 존재하지 않습니다.");
            result.put("code", HttpStatus.NOT_FOUND);
            e.printStackTrace();
		}
		
		return result;
	}
	
	// 댓글 등록
	@RequestMapping(value = "/board/comment", method = RequestMethod.POST)
	@ResponseBody
	Map<String, Object> comment(@RequestBody CommentDto commentDto, User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			String resultCommentId = commentService.insertComment(commentDto, user);
			Comment comment = commentService.getComment(resultCommentId);			
			result.put("comment", comment.showContent());
			result.put("code", HttpStatus.OK);
		} catch(Exception e) {
			result.put("msg", "해당 글이 존재하지 않습니다.");
            result.put("code", HttpStatus.NOT_FOUND);
            e.printStackTrace();
		}
		
		return result;
	}
	
	// 댓글 리스트
	@RequestMapping(value = "/board/comment", method = RequestMethod.GET)
	@ResponseBody Map<String, Object> getCommentList(@RequestParam String articleId/* XXX PathVariable로 바꿔야할까요? */, @RequestParam(defaultValue = "0") int page, User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		CommentParam commentParam = new CommentParam
				.Builder(pageSize, articleId)
				.useTotal(true)
				.useMore(true)
				.userId(user.getUserId())
				.build();
		
		PageList<Comment> pageList = commentService.getCommentList(commentParam);
		List<Comment> list = pageList.getList();
				
		List<CommentDto> commentList = list.stream()
				.map(Comment::showContent)
				.collect(Collectors.toList());
		
		result.put("page", page);
		result.put("commentList", commentList);
		
		return result;
	}
}
