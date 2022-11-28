
 "use strict";

var reservation = function(){
	
	var initForm = function(){
		var d = new Date();
		var year = d.getFullYear();
		var month = (d.getMonth() + 1);
		var date = d.getDate();
		var hour = d.getHours();
		var minute = d.getMinutes();
		
		if(month < 10)
			month = '0' + month;
		if(date < 10)
			date = '0' + date;
		if(hour < 10)
			hour = '0' + hour;
		if(minute < 10)
			minute = '0' + minute;
		
		var initTime = year + '-' + month + '-' + date  + 'T' + hour + ':' + minute;

		$('#time').val(initTime);
	}
	
	var buttonHandle = function(){
		$('.btn-reservation').on('click', function(){
			console.log("ini submit");
			
			swal.fire({
                title: 'Are you sure?',
                text: "Pilih tutor ini!",
                type: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Iya, saya pilih!'
            }).then(function(result) {
                if (result.value) {
						var data = {
						usernameTeacher: user.username,
						idUserCourse: course.idUserCourse,
						time: $('#time').val().replace('T',' '),
						duration: parseInt($('#duration').val()),
						telephone: $('#telephone').val()
					};
					$.ajax({
						url: "/reservation/save",
						type: "POST",
						dataType: "json",
						contentType: "application/json",
						data: JSON.stringify(data),
						success: function(response) {
							console.log(response);
							swal.fire(
		                        'Berhasil!',
		                        response.message,
		                        'success'
		                    )
							window.open(response.message);
						},
						error: function(response) {
							swal.fire(
		                        'Error!',
		                        response.message,
		                        'error'
		                    )
						}
					})
	
	
                    
                }
            });
			
			
			
		});
	}
	
	
	
	
	return {
		init: function(){
			buttonHandle();
			initForm();
		}
	}
}();

jQuery(document).ready(function() {
    reservation.init();
});
