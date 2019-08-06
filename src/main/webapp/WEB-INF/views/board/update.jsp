<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/resources/css/board.css" />
<script src="/resources/js/board.js"></script>
</head>
<body>
<div id="wrap">
	<div id="tb-wrap">
		<table class="table table-striped">
			<tr>
				<th>제목</th>
				<td><input type="text" id="title" value="${article.title }" />
			</tr>
			<tr>
				<th>작성자</th>
				<td>${article.userName }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="8" cols="50" id="contents">${article.contents }</textarea></td>
			</tr>
			<tr>
				<th><button type="button" id="update-btn" onclick="updateArticle('${article.link }', ${article.articleId })">수정</button></th>
				<td>--</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>