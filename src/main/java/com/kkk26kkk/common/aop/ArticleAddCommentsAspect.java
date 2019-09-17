package com.kkk26kkk.common.aop;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kkk26kkk.bbs.dao.CommentDao;
import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.bbs.model.Comment;
import com.kkk26kkk.bbs.model.CommentListDecorator;
import com.kkk26kkk.bbs.model.CommentParam;
import com.kkk26kkk.bbs.model.User;
import com.kkk26kkk.common.model.PageList;

@Aspect
@Component
public class ArticleAddCommentsAspect {
	@Autowired
	private CommentDao commentDao;
	
	private static final int COMMENT_PAGE_SIZE = 5;
	
	@Around("@annotation(com.kkk26kkk.common.aop.AddComments) && @ annotation(target)")
	public Object addComments(ProceedingJoinPoint joinPoint, AddComments target) { // TODO 리턴 안 하는 구조로 변경
		Object obj = null;
		try {
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		List<Article> articleList = null;
		if (obj instanceof PageList) {
			articleList = ((PageList<Article>) obj).getList();
		} else if (obj instanceof List) {
			articleList = (List<Article>) obj;
		} else if (obj instanceof Article) {
			articleList = new LinkedList<>();
			articleList.add((Article) obj);
		} else {	
			return obj;
		}
		
		String articleIds = articleList.stream()
				.map(Article::getArticleId)
				.collect(Collectors.joining(","));
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        
		CommentParam commentParam = new CommentParam
				.Builder(COMMENT_PAGE_SIZE, articleIds)
				.useMore(target.useMore())
				.useTotal(target.useTotal())
				.userId(user.getUserId())
				.build();
		
//		Map<String, PageList<Comment>> commentListMap = commentDao.selectCommentListMapMore(commentParam, Comment::getArticleId);
		Map<String, PageList<Comment>> commentListMap = commentDao.selectCommentListMapTotal(commentParam, Comment::getArticleId);
		
		for(int i = 0 ; i < articleList.size(); i++) {
			Article article = articleList.get(i);
			
			PageList<Comment> commentList = commentListMap.get(article.getArticleId());
			if(null == commentList) {
				continue;
			}
			
			articleList.set(i, new CommentListDecorator(article, commentList));
		}
		
		if (obj instanceof PageList) {
			((PageList<Article>) obj).setList(articleList);
		}
		return obj;
	}
}
