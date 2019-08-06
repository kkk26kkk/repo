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
		
		model.addAttribute("updateFormLink", Path.UpdateForm.getPath() + "?articleId=" + articleId);
		model.addAttribute("replyFormLink", Path.ReplyForm.getPath() + "?articleId=" + articleId);
		model.addAttribute("deleteLink", Path.Article.getPath() + "/" + articleId);
		model.addAttribute("commentLink", Path.Comment.getPath());
		
		return "/board/show";
	}
	
	// 글 작성 폼
	// 커스텀 인터페이스로 에스펙트 - @LoginRequire - user에서 isLogin 체크
	@RequestMapping(value = {"/board/write", "/board/reply"}) // PathVariable
	String writeForm(HttpServletRequest request, Model model, @RequestParam(defaultValue = "0") int articleId, User user) {
		ArticleDto articleDto = user.createArticle();
		System.out.println("userId" + user.getUserId());
		
		if(Path.ReplyForm.compare(request.getRequestURI())) {
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
	@RequestMapping(value = "/board/update")
	String updateForm(@RequestParam int articleId, Model model) {
		ArticleDto articleDto = articleService.getArticleDto(articleId);
		
		model.addAttribute("article", articleDto);
		
		return "/board/update";
	}
	
	// 글 등록 처리
	@RequestMapping(value = "/board", method = RequestMethod.POST)
	@ResponseBody
	Map<String, Object> write(HttpServletRequest request, @RequestBody ArticleDto articleDto, User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(!user.isUserId(articleDto.getUserId())) {
			result.put("msg", "로그인 정보가 다릅니다.");
            result.put("code", HttpStatus.FORBIDDEN);
		}
		
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
	
	// 글 수정 처리 // TODO /{articleId} 붙여서 쓰도록 수정
	@RequestMapping(value = "/board/{articleId}", method = RequestMethod.PUT)
	@ResponseBody
	Map<String, Object> update(@PathVariable int articleId, @RequestBody ArticleDto articleDto, User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			// TODO 자기글만 수정할 수 있도록 user 받아서 쓴다 - 뒤에 로직도 수정
			articleService.updateArticle(articleId, articleDto);
			result.put("code", HttpStatus.OK);
			result.put("redirect", Path.Article.getPath() + "/" + articleId);
		} catch(SQLException e) { // TODO 이런 식으로, 서비스에서 에러 던졌으면 잡아준다
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
	
	// 글 삭제 처리 // TODO /{articleId} 붙여서 쓰도록 수정
	@RequestMapping(value = "/board/{articleId}", method = RequestMethod.DELETE)
	@ResponseBody
	Map<String, Object> remove(@PathVariable int articleId, @RequestBody Article article, User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// TODO 글 작성자만 수정할 수 있도록
		
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
	@ResponseBody List<CommentDto> getCommentList(@RequestParam int articleId, @RequestParam(defaultValue = "1") int page, HttpServletRequest request, User user) {
		// TODO 댓글 페이징 처리
		// TODO service단에서 처리할 부분 이동
		
		PageListParam pageListParam = new PageListParam
				.Builder(page, pageSize)
				.useTotal(true)
				.useMore(true)
				.build();
		
		List<Comment> list = commentService.getCommentList(articleId);
		list.stream()
			.filter(comment -> /* !(comment.isCommentWriter(user.getUserId() 
									|| article.isArticleWriter(user.getUserId())))
									&& */ Code.COMMENT_SECRET_TYPE_PRIVATE.compare(comment.getCode()))
			.forEach(comment -> comment.setContents("비밀 댓글입니다."));
		list.stream()
			.filter(comment -> /* TODO !"관리자".equals(user.getGrade()) && */ Code.COMMENT_SECRET_TYPE_REPORTED.compare(comment.getCode()))
			.forEach(comment -> comment.setContents("신고 접수된 댓글입니다."));
		
		List<CommentDto> commentList = list.stream()
				.map(Comment::showComment)
				.collect(Collectors.toList());
		
		return commentList;
	}
}
