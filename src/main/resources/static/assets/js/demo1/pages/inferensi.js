/**
 * 
 */
 "use strict";
 
 var KTInferensi = function() {
 	
 	var welcome = function(){
 		console.log('Welcome to KTInferensi Page');
 	}
 
 	return {
 		init: function(){
 			welcome();
 		}
 	};
 }();
 
 jQuery(document).ready(function() {
    KTInferensi.init();
});