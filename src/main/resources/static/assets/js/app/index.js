/**
 * 
 */
 
 "use strict";

var index = function(){
	
	var showTeacherWidget= function(){
		console.log('this is index');
		console.log('cdnUrl : ' + cdnUrl);
		$.ajax({
			url: "/widget/teachers?subject=",
			type: "GET",
			success: function(response) {
				console.log(response)
				$.each(response.data, function(i, item){
					$('.showTeacherWidget').append('<div class="col-md-3"><div class="kt-widget kt-widget--user-profile-1"><div class="kt-widget__head"><div class="kt-widget__media"><img src="'+cdnUrl+item.foto+'" alt="image" style="min-width:100px"></div><div class="kt-widget__content"><div class="kt-widget__section"><a href="/pages/teacher/'+item.username+'" class="kt-widget__username">'+item.fullname+'<i class="flaticon2-correct kt-font-success"></i></a><span class="kt-widget__subtitle">' + truncate(item.summary) + '</span></div><div class="kt-widget__action"></div></div></div><div class="kt-widget__body"><div class="kt-widget__content"><div class="kt-widget__info"><span class="kt-widget__label">Tarif per jam:</span><a href="#" class="kt-widget__data">'+item.rate+'</a></div><div class="kt-widget__info"><span class="kt-widget__label">Subjek</span><a href="#" class="kt-widget__data">'+item.subject+'</a></div></div></div></div></div>');
				});
				
			},
			error: function(response) {
				console.log(response)
			}
		})
	}
	
	var truncate = function(str){
		var nmax=100;
		return (str.length > nmax) ? str.substr(0, nmax-1) + '&hellip;' : str;
	}
	
	
	return {
		init: function(){
			showTeacherWidget();
		}
	}
}();

jQuery(document).ready(function() {
    index.init();
});