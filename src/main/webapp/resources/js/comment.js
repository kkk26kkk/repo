$(function(){
	$( '#tb-comment > tbody').empty();
	
	$.ajax({
		url : "/comment",
		method : 'GET',
		data : {
			articleId : $('#a_id').val()
		}
	}).done(function(result){
		var str = '';
		$(result.articleComments).each(function(i, item) {
			str += '<tr>'
				 + '<td>' + item.userName + '</td>'
				 + '<td>' + item.contents + '</td>'
				 + '<td>' + item.regDtm + '</td>'
				 + '</tr>';
				 
		});
		$('#tb-comment > tbody').append(str);
	});
});

