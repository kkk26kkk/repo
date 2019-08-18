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
import com.kkk26kkk.bbs.model.Comment;
import com.kkk26kkk.bbs.model.CommentParam;
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

		model.addAttribute("writeFormLink", Path.WriteForm.getPath());
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
	String feedList(Model model, @RequestParam(defaultValue = "0") int page, User user) {
		ArticleParam articleParam = new ArticleParam
				.Builder(pageSize)
				.useTotal(true)
//				.useMore(true)
//				.sort(sort)
				.build();
		
        // XXX 글리스트, 댓글리스트를 service단에서 같이 처리해야 할까요?
        // TODO 글리스트는 서비스에서 DAO 직접 호출하고, 글별 댓글리스트를 붙이는 AOP 개발 (1개, List, PageList 대응)
		PageList<Article> pageArticleList = boardService.getFeedList(articleParam); 
		List<Article> articleList = pageArticleList.getList();
		
        String articleIdList = articleList.stream()
        // TODO Article에 getArticleId() 오버라이드, super.getArticleId() 호출
			.map(Article::getArticleId) // XXX getArticleId() -> public으로 열어줘야하지 않을까요?
			.collect(Collectors.joining(","));
		
        // XXX totalPage 방식으로 하려면 totalCount 처리를 어떻게 해야할까요?
        // TODO Param에서 useMore(true) 빼고 useTotal(true) 사용 -> PageList에서 getTotalPage() 사용
		CommentParam commentParam = new CommentParam
				.Builder(5, articleIdList)
				.useMore(true)
				.userId(user.getUserId())
				.build();
		
		PageList<Comment> pageCommentList = boardService.getFeedCommentList(commentParam);
		
		// XXX 댓글 리스트들이 해당 글 리스트와 짝을 지으려면 어떻게 해야할까요?
        // TODO PageList<Comment> 조회시 객체의 getArticleId() 기준으로 그룹핑 (Comment에 오버라이드 필요, 그룹기준 외부주입 필요)
        // Map<String, PageList<Comment>> -> 이후 Map의 key를 기준으로 CommentDto와 ArticleDto를 그룹핑
        // ArticleDto에 PageList<CommentDto> 추가 또는 Map으로 ArticleDto와 PageList<CommentDto> 조합
        // 팩토리 객체 추가 고려 -> PageList<Article>과 PageList<Comment>를 각각 PageList<ArticleDto>와 PageList<CommentDto>로 변환하는
        // VO와 DTO의 필드구조가 많이 달라졌을 때 빛을 발함, 같은 필드더라도 비즈니스 로직에 의해 값이 가공 되었을 때도 빛을 발함

		return "/board/feedList";
	}
}
