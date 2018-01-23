$('.header-btn-collapse-nav').on('click', function(e) {
    e.preventDefault();
});

$('#mainNav a').on('click', function() {
	$('.header-nav-main').removeClass('in');	
});
