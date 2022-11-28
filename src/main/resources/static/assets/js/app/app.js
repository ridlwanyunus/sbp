"use strict";

var landing = function(){
	
	var sayHello = function(){

	}
	
	
	return {
		init: function(){
			sayHello();
		}
	}
}();

jQuery(document).ready(function() {
    landing.init();
});

	var loginPage = function(){
		window.location.href='/login/student';
	}
	
	var loginTutor = function(){
		window.location.href='/login/tutor';
	}