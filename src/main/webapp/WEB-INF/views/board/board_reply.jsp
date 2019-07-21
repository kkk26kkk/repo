<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글 작성</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="resources/css/board.css" />
</head>
<body>
<div id="wrap">
	<div id="tb-wrap">
		<form action="board_reply_ok" method="post">
		<input type="hidden" name="groupNo" value="${board.groupNo }" />
		<input type="hidden" name="groupOrder" value="${board.groupOrder }" />
		<input type="hidden" name="groupLayer" value="${board.groupLayer }" />		
		<input type="hidden" name="page" value="${page }">	
			<table class="table table-striped">
				<tr>
					<th>아이디</th>
					<td>${userId }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value="RE:${board.title }"/></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="8" cols="50" name="contents"></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" value="등록" /></td>
					<td>--</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>