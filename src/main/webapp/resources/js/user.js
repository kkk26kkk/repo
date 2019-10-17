function userLogout(link) {
	$.ajax({
		url : link
	}).done(function(result){
		$(location).attr('href', result.redirect);
	});
	
	return false;
}
