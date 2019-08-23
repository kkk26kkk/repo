<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/resources/css/board.css" />
<script>
$(function(){
	$('#login-btn').on('click', function(){
		var data = {};
		data.userId = $('#userId').val();
		data.userPw = $('#userPw').val();
		
		$.ajax({
			url : '/user/login',
			method : 'POST',
			contentType : 'application/json;charset=utf-8',
			dataType : 'json',
			data : JSON.stringify(data)
		}).done(function(result){
			$(location).attr('href', result.redirect);
		});
	});
})
</script>
</head>
<body>
<div id="wrap">
	<div id="tb-wrap">
		<table class="table table-striped">
			<tr>
				<th>아이디</th>
				<td><input type="text" id="userId" name="userId" /></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" id="userPw" name="userPw" /></td>
			</tr>
			<tr>
				<td><button id="login-btn">로그인</button></td>
				<td>--</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>