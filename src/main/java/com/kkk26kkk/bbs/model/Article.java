package com.kkk26kkk.bbs.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.FastDateFormat;

import com.kkk26kkk.common.model.PageList;
import com.kkk26kkk.common.model.Path;

public class Article extends ArticleVo {
	private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
	
	private PageList<Comment> commentList;
	private int readCount;
	private int commentCount;
	private int popularity;
	private int rank;

	public PageList<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(PageList<Comment> commentList) {
		this.commentList = commentList;
	}

	public ArticleDto showHeader() {
		ArticleDto dto = new ArticleDto(getArticleId(), getUserName(), getTitle());
		dto.setReadCount(String.valueOf(getReadCount()));
		dto.setRegDtm(fdf.format(getRegDtm()));
		dto.setLink(Path.Board.getPath() + "/" + super.getArticleId());
		return dto;
	}
	
	public ArticleDto showContent() {
		ArticleDto dto = new ArticleDto(getArticleId(), getUserName(), getTitle(), getContents());
		dto.setReadCount(String.valueOf(getReadCount()));
		dto.setArticleId(getArticleId());
		dto.setUserId(getUserId());
		dto.setRegDtm(fdf.format(getRegDtm()));
		
		if(null != commentList) {
			List<CommentDto> commentDtoList = commentList.getList().stream()
					.map(Comment::showContent)
					.collect(Collectors.toList());
			
			// XXX ArticleDto에 PageList<CommentDto> 필드를 추가해야 할까요? 아니면 List<CommentDto>필드를 추가해야 할까요?
			dto.setCommentList(commentDtoList);
			dto.setCommentPage(commentList.getPage());
			dto.setCommentHasNext(commentList.hasNext());
		}
		
		return dto;
	}
		
	public static void ranking(List<Article> list, String keyword) {
		int rank = 1;
		
		if("readCount".equals(keyword)) {
			list.sort(Comparator.comparing(Article::getReadCount).reversed());
		} else if("commentCount".equals(keyword)) {
			list.sort(Comparator.comparing(Article::getCommentCount).reversed());
		} else if("popularity".equals(keyword)) {
			list.sort(Comparator.comparing(Article::getPopularity).reversed());
		}
		for(Article article : list) {
			article.setRank(rank++);
		}
		list.sort(Comparator.comparing(Article::getArticleId));
	}
	
	@Override
	public String getArticleId() {
		return super.getArticleId();
	}

	public int getReadCount() {
		return this.readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getCommentCount() {
		return commentCount;
	}
	
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
