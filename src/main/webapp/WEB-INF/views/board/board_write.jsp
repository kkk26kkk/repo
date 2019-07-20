<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="../resources/css/board.css" />
</head>
<body>
<div id="wrap">
	<div id="tb-wrap">
		<form action="board_write_ok" method="post">
			<table class="table table-striped">
				<tr>
					<th>아이디</th>
					<td>${userId }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" /></td>
				</tr>
				<tr>
					<th>내용</th>
<!-- 					<td><input type="text" name="contents" /></td> -->
					<td><textarea name="contents"></textarea></td>
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