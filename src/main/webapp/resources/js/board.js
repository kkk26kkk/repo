function updateArticle(link, articleId) {
	var data = {};
	data.articleId = articleId;
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

function deleteArticle(link, articleId) {
	var data = {};
	data.articleId = articleId;
	
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

function writeComment(link, articleId) {
	var data = {};
	data.articleId = articleId;
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

function getTimeStamp() {
	var d = new Date();
	
	var s =
	leadingZeros(d.getFullYear(), 4) + '-' +
	leadingZeros(d.getMonth() + 1, 2) + '-' +
	leadingZeros(d.getDate(), 2) + ' ' +
	
	leadingZeros(d.getHours(), 2) + ':' +
	leadingZeros(d.getMinutes(), 2);

  return s;
}



function leadingZeros(n, digits) {
	var zero = '';
	n = n.toString();
	
	if (n.length < digits) {
		for (i = 0; i < digits - n.length; i++)
			zero += '0';
	}
		
	return zero + n;
}
