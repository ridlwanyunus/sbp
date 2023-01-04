/**
 * 
 */
 "use strict";
 
 var KTInferensi = function() {
 	
 	var param = {};
 	
 	var welcome = function(){
 		console.log('Welcome to KTInferensi Page');
 	}
 	
 	var buttonHandler = function () {
		$('#berlebih').on('click', function(){
			param.keuangan = 'Berlebih';
			questionOneOut();
			questionTwo();
			
		});
		$('#cukup').on('click', function(){
			param.keuangan = 'Cukup';
			questionOneOut();
			questionTwo();
			
		});
		$('#kurang').on('click', function(){
			param.keuangan = 'Kurang';
			questionOneOut();
			questionTwo();
			
		});
		
		// Question 2
		$('#perhatian').on('click', function(){
			param.sikap = 'Perhatian';
			questionTwoOut();
			questionThree();
		});
		$('#cuek').on('click', function(){
			param.sikap = 'Cuek';
			questionTwoOut();
			questionThree();
		});
		$('#romantis').on('click', function(){
			param.sikap = 'Romantis';
			questionTwoOut();
			questionThree();
		});


		// Question 3
		$('#terbuka').on('click', function(){
			param.komunikasi = 'Terbuka';
			questionThreeOut();
			questionFour();
		});
		$('#reponsif').on('click', function(){
			param.komunikasi = 'Responsif';
			questionThreeOut();
			questionFour();
		});
		$('#pendengar').on('click', function(){
			param.komunikasi = 'Pendengar yang Baik';
			questionThreeOut();
			questionFour();
		});
		$('#satuArah').on('click', function(){
			param.komunikasi = 'Hanya 1 Arah';
			questionThreeOut();
			questionFour();
		});
		
		
		// Question 4
		$('#berprestasi').on('click', function(){
			param.kecerdasan = 'Beprestasi';
			questionFourOut();
			questionFive();
		});
		$('#explor').on('click', function(){
			param.kecerdasan = 'Suka Explorasi';
			questionFourOut();
			questionFive();
		});
		$('#kurangExplorasi').on('click', function(){
			param.kecerdasan = 'Kurang Explorasi';
			questionFourOut();
			questionFive();
		});
		
		// Question 5
		$('#lucu').on('click', function(){
			param.humoris = 'Lucu';
			questionFiveOut();
			console.log(param);
			predict(param);
		});
		$('#serius').on('click', function(){
			param.humoris = 'Serius';
			questionFiveOut();
			console.log(param);
			predict(param);
		});
		$('#receh').on('click', function(){
			param.humoris = 'Receh';
			questionFiveOut();
			console.log(param);
			predict(param);
		});
	}
	
	var questionOne = function () {
		$('#question-one').css('display', 'block');
		$('#question-one').addClass('fadeUp');
	}
	
	var questionTwo = function () {
		$('#question-two').css('display', 'block');
		$('#question-two').addClass('fadeUp');
	}
	
	var questionThree = function () {
		$('#question-three').css('display', 'block');
		$('#question-three').addClass('fadeUp');
	}
	
	var questionFour = function () {
		$('#question-four').css('display', 'block');
		$('#question-four').addClass('fadeUp');
	}
	
	var questionFive = function () {
		$('#question-five').css('display', 'block');
		$('#question-five').addClass('fadeUp');
	}
	
	var questionOneOut = function () {
		$('#question-one').css('display', 'none');
	}
	
	var questionTwoOut = function () {
		$('#question-two').css('display', 'none');
	}
	
	var questionThreeOut = function () {
		$('#question-three').css('display', 'none');
	}
	
	var questionFourOut = function () {
		$('#question-four').css('display', 'none');
	}
	
	var questionFiveOut = function () {
		$('#question-five').css('display', 'none');
	}
	
	var predict = function (param){
		$.ajax({
			url: '/predict',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(param),
			success: function(response){
				console.log(response);
				if(response.data == 'Ya'){
					swal.fire('Iya','Ya Anda adalah pasangan yang idaman','success');
				} else if(response.data == 'Tidak') {
					swal.fire('Tidak','Anda bukan pasangan yang idaman','error');
				} else {
					swal.fire('Oops',response.message,'error');
				}
			},
			error: function(){
				swal.fire('Oops',response.message,'error');
			}
		});
	}
 
 	return {
 		init: function(){
 			welcome();
 			buttonHandler();
 			questionOne();
 		}
 	};
 }();
 
 jQuery(document).ready(function() {
    KTInferensi.init();
});