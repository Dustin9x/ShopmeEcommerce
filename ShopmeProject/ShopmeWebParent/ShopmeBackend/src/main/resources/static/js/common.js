$(document).ready(function(){
		$("#logoutlink").on("click", function(e){
			e.preventDefault();
			document.logoutForm.submit();
		});
	});