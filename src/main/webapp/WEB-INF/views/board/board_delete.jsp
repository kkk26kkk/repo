<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="../resources/css/board.css" />
<script>
function del_check() {
	if($('input[type=password]').val() == '') {
		alert('비밀번호를 입력하세요.');
		$('input[type=password]').val('').focus();
		return false;
	}
}
</script>
</head>
<body>
<div id="wrap">
	<div id="tb-wrap">
		<form action="board_delete_ok" method="post" onsubmit="return del_check()">
<!-- 			<input type="hidden" name="_method" value="delete" /> -->
			<input type="hidden" name="idx" value="${idx }" />
			<table class="table table-striped">
				<tr>
					<th>삭제 비밀번호</th>
					<td><input type="password" name="pw" /></td>
				</tr>
				<tr>
					<th><input type="submit" />삭제</th>
					<td>--</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>