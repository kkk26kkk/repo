package com.kkk26kkk.common.aop;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkk26kkk.bbs.dao.CommentDao;
import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.Comment;
import com.kkk26kkk.bbs.model.CommentParam;
import com.kkk26kkk.common.model.PageList;

@Aspect
@Component
public class ArticleAddContentsAspect {
	@Autowired
	private CommentDao commentDao;
	
	private static final int COMMENT_PAGE_SIZE = 5;
	
	@Around("@annotation(com.kkk26kkk.common.aop.AddComments)")
	public Object addComments(ProceedingJoinPoint joinPoint) {
		Object obj = null;
		try {
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		List<Article> articleList = new LinkedList<>();
		if (obj instanceof PageList) {
			articleList = ((PageList<Article>) obj).getList();
		} else if (obj instanceof List) {
			articleList = (List<Article>) obj;
		} else if (obj instanceof Article) {
			articleList.add((Article) obj);
		} else {	
			return obj;
		}
		
		String articleIdList = articleList.stream()
				.map(Article::getArticleId)
				.collect(Collectors.joining(","));
        
        // TODO HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest(); 사용
		CommentParam commentParam = new CommentParam
				.Builder(COMMENT_PAGE_SIZE, articleIdList)
				.useMore(true)
				.userId("abcd") // XXX 현재 접속한 유저 아이디를 어떻게 주입시켜야 할까요?
				.build();
		
		Function<Comment, String> groupById = c -> c.getArticleId();
		
		Map<String, PageList<Comment>> commentListMap = commentDao.getFeedCommentList(commentParam, groupById);
		
		articleList.stream()
			.forEach(a -> a.setCommentList(commentListMap.get(a.getArticleId())));
				
		return obj;
	}
}
