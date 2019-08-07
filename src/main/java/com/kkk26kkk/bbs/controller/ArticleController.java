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
import com.kkk26kkk.bbs.model.User;
import com.kkk26kkk.bbs.service.ArticleService;
import com.kkk26kkk.bbs.service.CommentService;
import com.kkk26kkk.common.model.Code;
import com.kkk26kkk.common.model.PageListParam;
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
	String show(@PathVariable int articleId, Model model) {
		Article article = articleService.getArticle(articleId);
		
		model.addAttribute("article", article.showContent());
		
		model.addAttribute("updateFormLink", Path.Article.getPath() + "/" + articleId + "/update");
		model.addAttribute("replyFormLink", Path.Article.getPath() + "/" + articleId + "/reply");
		model.addAttribute("deleteLink", Path.Article.getPath() + "/" + articleId);
		model.addAttribute("commentLink", Path.Comment.getPath());
		
		return "/board/show";
	}
	
	// 글 작성 폼
	// 커스텀 인터페이스로 에스펙트 - @LoginRequire - user에서 isLogin 체크
	@RequestMapping(value = {"/board/write", "/board/{articleId}/reply"})
	String writeForm(HttpServletRequest request, Model model, @PathVariable int articleId, User user) {
		ArticleDto articleDto = user.createArticle();
		
//		String replyForm = Path.Article.getPath() + "/" + articleId + "/reply"; 
		
		if(Path.ReplyForm.compare(request.getRequestURI())) { // XXX PathVariable로 바꾸면 경로 비교 어떻게?
			ArticleDto article = articleService.getArticleDto(articleId);
			
			articleDto.setArticleId(article.getArticleId());
			articleDto.setTitle("RE:" + article.getTitle());
		} 
		
		model.addAttribute("article", articleDto);
//		articleDto = null;
		model.addAttribute("path", Path.Article.getPath());
		
		return "/board/write";
	}
	
	// 글 수정 폼
	@RequestMapping(value = "/board/{articleId}/update")
	String updateForm(@PathVariable int articleId, Model model) {
		ArticleDto articleDto = articleService.getArticleDto(articleId);
		
		model.addAttribute("article", articleDto);
		
		return "/board/update";
	}
	
	// 글 등록 처리
	@RequestMapping(value = "/board", method = RequestMethod.POST)
	@ResponseBody
	Map<String, Object> write(HttpServletRequest request, @RequestBody ArticleDto articleDto, User user) {
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
	Map<String, Object> update(@PathVariable int articleId, @RequestBody ArticleDto articleDto, User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(!user.isUserId(articleDto.getUserId())) {
			result.put("msg", "로그인 정보가 다릅니다.");
            result.put("code", HttpStatus.FORBIDDEN);
		}
		
		try {
			articleService.updateArticle(articleId, articleDto);
			result.put("code", HttpStatus.OK);
			result.put("redirect", Path.Article.getPath() + "/" + articleId);
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
	Map<String, Object> remove(@PathVariable int articleId, @RequestBody ArticleDto articleDto, User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(!user.isUserId(articleDto.getUserId())) {
			result.put("msg", "로그인 정보가 다릅니다.");
            result.put("code", HttpStatus.FORBIDDEN);
		}
		
		try {
			articleService.deleteArticle(articleId);
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
	@RequestMapping(value = "/board/comment", method = RequestMethod.POST)
	@ResponseBody
	Map<String, Object> comment(@RequestBody CommentDto commentDto, HttpServletRequest request, User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			int resultCommentId = commentService.insertComment(commentDto, user);
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
	@RequestMapping(value = "/board/comment", method = RequestMethod.GET) // TODO @PathVariable
	@ResponseBody List<CommentDto> getCommentList(@RequestParam int articleId, @RequestParam String articleUserId, @RequestParam(defaultValue = "1") int page, HttpServletRequest request, User user) {
		PageListParam pageListParam = new PageListParam
				.Builder(page, pageSize)
				.useTotal(true)
				.useMore(true)
				.build();
		
		// TODO PageList로
		List<Comment> list = commentService.getCommentList(articleId, pageListParam);
		list.stream()
			.filter(comment -> !( user.isUserId(comment.getUserId()) || user.isUserId(articleUserId) )
									&&  Code.COMMENT_SECRET_TYPE_PRIVATE.compare(comment.getCode()))
			.forEach(comment -> comment.setContents("비밀 댓글입니다."));
		list.stream()
			.filter(comment -> /* TODO !UserGrade.SUPER_USER.compare(user.getGrade()) && */ Code.COMMENT_SECRET_TYPE_REPORTED.compare(comment.getCode()))
			.forEach(comment -> comment.setContents("신고 접수된 댓글입니다."));
		
		List<CommentDto> commentList = list.stream()
				.map(Comment::showContent)
				.collect(Collectors.toList());
		
		return commentList;
	}
}
