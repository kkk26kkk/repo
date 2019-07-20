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

<link rel="stylesheet" href="../resources/css/board.css" />
<script>
function edit_check() {
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
		<form action="board_edit_ok" method="post" onsubmit="return edit_check()">
<!-- 			<input type="hidden" name="_method" value="put" /> -->
			<input type="hidden" name="idx" value="${board.idx }" />
			<table class="table table-striped">
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value="${board.title }" />
				</tr>
				<tr>
					<th>작성자</th>
					<td>${board.userName }</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${board.regDtm }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><input type="text" name="contents" value="${board.contents }" /></td>
				</tr>
				<tr>
					<th><input type="submit" value="수정" /></th>
					<td>--</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>