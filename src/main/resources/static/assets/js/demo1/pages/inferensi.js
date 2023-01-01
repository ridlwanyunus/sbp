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
		$('#miskin').on('click', function(){
			param.keuangan = 'Miskin';
			questionOneOut();
			questionTwo();
			
		});
		$('#cukup').on('click', function(){
			param.keuangan = 'Cukup';
			questionOneOut();
			questionTwo();
			
		});
		$('#kaya').on('click', function(){
			param.keuangan = 'Kaya';
			questionOneOut();
			questionTwo();
			
		});
		
		// Question 2
		$('#cuek').on('click', function(){
			param.sikap = 'Cuek';
			questionTwoOut();
			questionThree();
		});
		$('#perhatian').on('click', function(){
			param.sikap = 'Perhatian';
			questionTwoOut();
			questionThree();
		});
		
		// Question 3
		$('#nyambung').on('click', function(){
			param.komunikasi = 'Nyambung';
			questionThreeOut();
			questionFour();
		});
		$('#tidakNyambung').on('click', function(){
			param.komunikasi = 'Tidak Nyambung';
			questionThreeOut();
			questionFour();
		});
		
		
		// Question 4
		$('#cerdas').on('click', function(){
			param.kecerdasan = 'Cerdas';
			questionFourOut();
			questionFive();
		});
		$('#pintar').on('click', function(){
			param.kecerdasan = 'Pintar';
			questionFourOut();
			questionFive();
		});
		$('#sedang').on('click', function(){
			param.kecerdasan = 'Sedang';
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