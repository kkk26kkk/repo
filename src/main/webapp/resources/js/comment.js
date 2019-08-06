var com = com || {};
com.kkk26kkk = com.kkk26kkk || {};
com.kkk26kkk.bbs = com.kkk26kkk.bbs || {};

com.kkk26kkk.bbs.comments = (function() {

	var renderComments = function() {
		$( '#tb-comment > tbody').empty();
		
		$.ajax({
			url : "/comment",
			method : 'GET',
			data : {
				articleId : $('#a_id').val()
			}
		}).done(function(result){
//			var commentList = JSON.parse(result);
			var commentList = result;
			
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


