<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="../resources/css/board.css" />
<script>
// $(function(){
// 	$('#delete-btn').click(function(){
// 		location.href = 'board_delete';
// 	});
// });
</script>
</head>
<body>
<div id="wrap">
	<div id="tb-wrap">
		<table class="table table-striped">
			<tr>
				<th>제목</th>
				<td>${board.title }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${board.regDtm }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${board.contents }</td>
			</tr>
			<tr>
				<td><button id="update-btn" onclick="location='board_edit?idx=${board.idx }'">수정</button></td>
				<td><button id="delete-btn" onclick="location='board_delete?idx=${board.idx }'">삭제</button></td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>