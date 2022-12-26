/**
 * 
 */
 "use strict";
 
 var KTKnowledgeRepresentation = function() {
 	
 	var welcome = function(){
 		console.log('Welcome to KTKnowledgeRepresentation Page');
 	}
 
 	return {
 		init: function(){
 			welcome();
 		}
 	};
 }();
 
 jQuery(document).ready(function() {
    KTKnowledgeRepresentation.init();
});