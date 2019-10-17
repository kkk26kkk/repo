<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/resources/css/common.css" />
<script src="/resources/js/articles.js"></script>
<script src="/resources/js/user.js"></script>
<script>

</script>
</head>
<body>
<div id="wrap">
	<div id="tb-wrap">
		<h3>게시판</h3>
		<div>
			<c:if test="${sessionScope.user.userId == null }">
				<a href="${loginFormLink }">로그인</a>
				<a href="${signUpFormLink }">회원가입</a>
			</c:if>
			
			<c:if test="${sessionScope.user.userId != null }">
					<span>아이디 : ${sessionScope.user.userId } 이름 : ${sessionScope.user.userName }</span>
					<a href="${writeFormLink }">글쓰기</a>
					<a href="#" onclick="userLogout('${logoutLink }')">로그아웃</a>
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
        	<c:set var="num" value="${totalCount-(page-1)*10 }"/>
			
			<c:forEach var="a" items="${articles }">
				<tr>
					<td>
						<!-- 번호 출력 부분 -->
			            <c:out value="${num }"/>
			            <c:set var="num" value="${num-1 }"/>
					</td>
					<td><a href="${a.link }">${a.title }</a>
					<td>${a.userName }</td>
					<td>${a.regDtm }</td>
				</tr>
			</c:forEach>
		</table>
		
		<div style="margin-left: 0px; margin-top: 20px;">
	        <c:if test="${page <= 1 }">
	        	[이전]&nbsp;
	        </c:if>
	        
	        <c:if test="${page > 1 }">
	        	<a href="/articles?page=${page - 1 }">[이전]</a>&nbsp;
	        </c:if>
	        
	        <c:forEach var="a" begin="${startPage }" end="${endPage }">
		        <c:if test="${a == page }">
	            	[${a }]
	          	</c:if>
	          	<c:if test="${a != page }">
	            	<a href="/articles?page=${a }">[${a }]</a>&nbsp;
	          	</c:if>
	        </c:forEach>
	        
	        <c:if test="${page >= totalPage }">
	        	[다음]
	        </c:if>
	        <c:if test="${page < totalPage }">
	        	<a href="/articles?page=${page + 1 }">[다음]</a>
	        </c:if>
		</div>
	</div>
</div>
</body>
</html>