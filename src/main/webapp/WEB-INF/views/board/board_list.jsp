<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="resources/css/board.css" />
</head>
<body>
<div id="wrap">
	<div id="tb-wrap">
		<div>
			<c:if test="${sessionScope.userId == null }">
				<a href="login">로그인</a>
			</c:if>
			
			<c:if test="${sessionScope.userId != null }">
					<span>아이디 : ${userId }</span>
					<a href="board_write">글쓰기</a>
					<a href="logout">로그아웃</a>
			</c:if>
		</div>
		<table class="table table-striped">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			
			<!-- 화면 출력 번호 변수 정의 -->
        	<c:set var="num" value="${listcount-(page-1)*10 }"/>
			
			<c:forEach var="board" items="${boardList }">
				<tr>
					<td>
						<!-- 번호 출력 부분 -->
			            <c:out value="${num }"/>
			            <c:set var="num" value="${num-1 }"/>
					</td>
					<td>
						<c:if test="${b.bbs_re_lev != 0 }">
		            	    <c:forEach var="k" begin="1" end="${board.groupOrder }">
			                  &nbsp;&nbsp;
			                </c:forEach>
			            </c:if>
					
						<!-- 제목 출력 부분 -->
						<a href="#{board.link }">${board.title }</a>
						<a href="board_view?page=${page }&idx=${board.idx}">${board.title }</a>
					</td>
					<td>${board.userName }</td>
					<td>${board.regDtm }</td>
				</tr>
			</c:forEach>
		</table>
		
		<div style="margin-left: 0px; margin-top: 20px;">
	        <c:if test="${page<=1 }">
	          [이전]&nbsp;
	        </c:if>
	        
	        <c:if test="${page>1 }">
	          <a href="board_list?page=${page-1 }">[이전]</a>&nbsp;
	        </c:if>
	        
	        <c:forEach var="a" begin="${startpage }" end="${endpage }">
	          <c:if test="${a==page }">
	            [${a }]
	          </c:if>
	          <c:if test="${a!=page }">
	            <a href="board_list?page=${a }">[${a }]</a>&nbsp;
	          </c:if>
	        </c:forEach>
	        
	        <c:if test="${page>=maxpage }">
	          [다음]
	        </c:if>
	        <c:if test="${page<maxpage }">
	          <a href="board_list?page=${page+1 }">[다음]</a>
	        </c:if>
		</div>
	</div>
</div>
</body>
</html>