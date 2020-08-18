package com.kkk26kkk.bbs.article.controller;

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

import com.kkk26kkk.bbs.article.model.Article;
import com.kkk26kkk.bbs.article.model.ArticleDto;
import com.kkk26kkk.bbs.article.service.ArticleService;
import com.kkk26kkk.bbs.comment.model.Comment;
import com.kkk26kkk.bbs.comment.model.CommentDto;
import com.kkk26kkk.bbs.comment.model.CommentParam;
import com.kkk26kkk.bbs.comment.service.CommentService;
import com.kkk26kkk.common.code.Path;
import com.kkk26kkk.common.exception.BizException;
import com.kkk26kkk.common.model.PageList;
import com.kkk26kkk.user.model.User;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	private static final int PAGE_SIZE = 10;
	
	// TODO @모니터링(name="ArticleController.show", group="ALL")
	@RequestMapping(value = "/articles/{articleId}", method = RequestMethod.GET)
	String showArticle(Model model, User user, @PathVariable String articleId) {
		Article article = articleService.getArticle(articleId);
		
		try {
			articleService.insertReadCount(articleId, user.getUserId());
		} catch (BizException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("article", article.showContent());
		model.addAttribute("loginUserId", user.getUserId());
		model.addAttribute("updateForm", Path.Modify.getRestPath(articleId));
		model.addAttribute("replyForm", Path.Reply.getRestPath(articleId));
		model.addAttribute("delete", Path.Articles.getRestPath(articleId));
		model.addAttribute("comments", Path.Comments.getRestPath(articleId));
		
		return "/articles/article";
	}
	
	@RequestMapping(value = {"/articles/write", "/articles/{articleId}/reply"})
	String articleForm(HttpServletRequest request, Model model, User user, @PathVariable(required = false) String articleId) {
		ArticleDto articleDto = user.createArticle();
		
		if(Path.Reply.containedTo(request.getRequestURI())) {
			Article article = articleService.getArticle(articleId);
			ArticleDto parentArticleDto = article.showContent();
			
			articleDto.setArticleId(parentArticleDto.getArticleId());
			articleDto.setTitle("RE:" + parentArticleDto.getTitle());
		}
		
		model.addAttribute("article", articleDto);
		model.addAttribute("path", Path.Articles.getPath());
		
		return "/articles/form";
	}
	
	@RequestMapping(value = "/articles/{articleId}/modify")
	String modifyForm(Model model, @PathVariable String articleId) {
		Article article = articleService.getArticle(articleId);
		ArticleDto articleDto = article.showContent();
		
		model.addAttribute("article", articleDto);
		model.addAttribute("path", Path.Articles.getRestPath(articleId));
		
		return "/articles/modify";
	}
	
	@RequestMapping(value = "/articles", method = RequestMethod.POST)
	@ResponseBody Map<String, Object> writeArticle(User user, @RequestBody ArticleDto articleDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			articleService.insertArticle(articleDto, user);
			map.put("code", HttpStatus.OK);
			map.put("redirect", Path.Articles.getPath());
		} catch(BizException e) {
			map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		} catch(Exception e) {
			map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping(value = "/articles/{articleId}", method = RequestMethod.PUT)
	@ResponseBody Map<String, Object> modifyArticle(User user, @PathVariable String articleId, @RequestBody ArticleDto articleDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(!user.isUserId(articleDto.getUserId())) {
			map.put("code", HttpStatus.FORBIDDEN);
			map.put("msg", "로그인 정보가 다릅니다.");
			
			return map;
		}
		
		try {
			articleService.updateArticle(articleId, articleDto);
			map.put("code", HttpStatus.OK);
			map.put("redirect", Path.Articles.getPath() + "/" + articleId);
		} catch(BizException e) {
			map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		} catch(Exception e) {
			map.put("code", HttpStatus.NOT_FOUND);
			map.put("msg", "해당 글이 존재하지 않습니다.");
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping(value = "/articles/{articleId}", method = RequestMethod.DELETE)
	@ResponseBody Map<String, Object> removeArticle(User user, @PathVariable String articleId, @RequestBody ArticleDto articleDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(!user.isUserId(articleDto.getUserId())) {
			map.put("code", HttpStatus.FORBIDDEN);
			map.put("msg", "로그인 정보가 다릅니다.");
			
			return map;
		}
		
		try {
			articleService.deleteArticle(articleId);
			map.put("code", HttpStatus.OK);
		} catch(BizException e) {
			map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("msg", e.getMessage());
            e.printStackTrace();
		} catch(Exception e) {
			map.put("code", HttpStatus.NOT_FOUND);
			map.put("msg", "해당 글이 존재하지 않습니다.");
            e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping(value = "/articles/{articleId}/comments", method = RequestMethod.POST)
	@ResponseBody Map<String, Object> writeComment(User user, @RequestBody CommentDto commentDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			String resultCommentId = commentService.insertComment(commentDto, user);
			Comment comment = commentService.getComment(resultCommentId);			
			map.put("code", HttpStatus.OK);
			map.put("comment", comment.showContent());
		} catch(BizException e) {
			map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("msg", e.getMessage());
            e.printStackTrace();
		} catch(Exception e) {
			map.put("code", HttpStatus.NOT_FOUND);
			map.put("msg", "해당 글이 존재하지 않습니다.");
            e.printStackTrace();
		}
		
		return map;
	}
	
    @RequestMapping(value = "/articles/{articleId}/comments", method = RequestMethod.GET)
	@ResponseBody Map<String, Object> showComments(User user, @PathVariable String articleId, @RequestParam(defaultValue = "1") int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		CommentParam commentParam = new CommentParam
				.Builder(PAGE_SIZE, articleId)
				.useTotal(true)
				.useMore(true)
				.userId(user.getUserId())
				.build();
		
		try {
			PageList<Comment> pageList = commentService.getCommentList(commentParam);
			List<Comment> list = pageList.getList();
					
			List<CommentDto> commentDtoList = list.stream()
					.map(Comment::showContent)
					.collect(Collectors.toList());
			
			map.put("page", page);
			map.put("comments", commentDtoList);
		} catch(BizException e) {
			map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		return map;
	}
}
