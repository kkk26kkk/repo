package com.kkk26kkk.bbs.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkk26kkk.bbs.model.Article;
import com.kkk26kkk.common.model.PageListParam;
import com.kkk26kkk.common.model.PageList;

@Repository
public class BoardDao extends BaseDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// TODO Page 객체를 빌더패턴으로 만들고 -> page, pageSize -> start, end는 get 만 있어도 될 듯?
	
	// TODO List를 적재할 객체 만들고 -> list, page, pageSize, totalCount, totalPage, hasNext, totalPage(getter)
	
	public PageList<Article> getArticleList(PageListParam pageDto) { /** totalPage **/
		return super.getPageListTotal("getArticleList", "getArticleCount", pageDto);
	}
	
	public int getArticleCount() {
		return sqlSession.selectOne("getArticleCount");
	}
	
	// TODO PageDao 같은 거 만들고 -> selectList 를 내부에서 하는 -> selectPageList 같은 메소드 만들어준다
	
	public PageList<Article> getArticleListMore(int page) { /** hasNext **/
//		return PageDao.selectPageList("getArticleList", page); 결국이거 -> 리턴은 PageList<Article>
		
		
//		PageList<Article> pageList = super.getArticleListMore("getArticleList", page);
		
		
		/**
		 * 
		page.setEndNum(page.getEndNum() + 1);
		
		List<Article> list = sqlSession.selectList("getArticleList", page);
		
		boolean hasNext = false;
		if(page.getPageSize() < list.size()) {
			hasNext = true;
		}
		
		if(hasNext) {
			list.remove(page.getPageSize());
		}
		
		// TODO hasNext 값 생겼음
		
		return list;
		
		*/
		
		return null;
	}
	
}
