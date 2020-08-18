<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/resources/css/common.css" />
<script src="/resources/js/articles.js"></script>
</head>
<body>
<div id="wrap">
	<div id="tb-wrap">
		<h3>게시글 보기</h3>
		<table class="table table-striped">
			<tr>
				<th>제목</th>
				<td><span id="title" data-article-id="${article.articleId}">${article.title}</span></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td id="comments" data-show-comments="${comments}">${article.userName}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${article.regDtm}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><span id="contents">${article.contents}</span></td>
			</tr>
			<c:if test="${loginUserId != ''}">
				<c:if test="${loginUserId == article.userId}">
					<tr>
						<td><button id="update-btn" onclick="location='${updateForm}'">수정</button></td> <!-- full link로 -->
						<td><button id="delete-btn" onclick="deleteArticle('${delete}')">삭제</button></td>
					</tr>
				</c:if>
				<c:if test="${loginUserId != article.userId}">
					<tr>
						<td><button type="button" onclick="location='${replyForm}'">답변</button></td>
						<td>--</td>
					</tr>
				</c:if>
			</c:if>
		</table>
		
		<h3>댓글</h3>
		<table id="tb-comment" class="table table-striped">
			<tbody>
			
			</tbody>
		</table>
		
		<textarea id=c-contents rows="2" cols="120"></textarea>
		<input type="checkbox" id="isSecret" name="isSecret"> 
		<button type="button" onclick="writeComment('${comments}')">댓글 등록</button>
	</div>
</div>

<script>
$(function() {
	com.kkk26kkk.bbs.comments.renderComments();
})

var com = com || {};
com.kkk26kkk = com.kkk26kkk || {};
com.kkk26kkk.bbs = com.kkk26kkk.bbs || {};

com.kkk26kkk.bbs.comments = (function() {

	var renderComments = function() {
		$('#tb-comment > tbody').empty();
		
		$.ajax({
			url : $('#comments').attr('data-show-comments'),
			method : 'GET',
			data : {
				articleId : $("#title").attr("data-article-id")
			}
		}).done(function(result){
			var commentList = result.comments;
			
			var comments = commentList
			.map(function(comment) {
				var string = '';
				string += '<tr>';
				string += '<td>' + comment.userName + '</td>';
				string += '<td>' + comment.contents + '</td>';
				string += '<td>' + comment.regDtm + '</td>';
				string += '</tr>';
				return string;
			})
			.join('')
			
			var html = $($.parseHTML(comments)).filter("tr");
			
			$('#tb-comment > tbody').append(html);
		});
	}
	
 	return {
 		renderComments: renderComments
	}
}());
</script>
</body>
</html>