function updateArticle(link, articleId) {
	var data = {};
	data.userId = $("#userName").attr("data-user-id");
	data.title = $('#title').val();
	data.contents = $('#contents').val();
	
	$.ajax({
		url : link,
		method : 'PUT',
		contentType : 'application/json;charset=utf-8',
		dataType : 'json',
		data : JSON.stringify(data)
	}).done(function(result){
		$(location).attr('href', result.redirect);
	});
}

function deleteArticle(link) {
	var data = {};
	data.userId = $("#userName").attr("data-user-id");
	
	$.ajax({
		url : link,
		method : 'DELETE',
		contentType : 'application/json;charset=utf-8',
		dataType : 'json',
		data : JSON.stringify(data)
	}).done(function(result){
		$(location).attr('href', result.redirect);
	});
}

function writeComment(link) {
	var data = {};
	data.articleId = $("#title").attr("data-article-id");
	data.contents = $('#c-contents').val();
	
	$.ajax({
		url : link,
		method : 'POST',
		contentType : 'application/json;charset=utf-8',
		dataType : 'json',
		data : JSON.stringify(data)
	}).done(function(result){
		var str = '';
		str += '<tr>'
			 + '<td>' + result.userName + '</td>'
			 + '<td>' + result.contents + '</td>'
			 + '<td>' + getTimeStamp() + '</td>'
			 + '</tr>';
			 
		$('#tb-comment').append(str);
		$('#c-contents').val('');
	});
}
