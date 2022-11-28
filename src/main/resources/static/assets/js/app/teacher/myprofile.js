/**
 * 
 */

"use strict";

var myprofile = function() {

	var sayHello = function() {

	}
	var eventMyProfile = function() {
		$('#profile_avatar').change(function() {
			updateThumbnail(this);
		})
		
		$.ajax({
			url: "/userprofile/course",
			type: "GET",
			contentType: "application/json",
			success: function(response) {
				console.log(response)
				var data = response.data;
				$('#subject').val(data.subject);
				$('#rate').val(data.rate);
				$('#language').val(data.language);
				var level = data.level;
				setLevelCheckbox(level);
				
			},
			error: function(response) {
				console.log(response)
			}
		})

		$('#btn-course').click(function(){
			
			swal.fire({
                title: 'Are you sure?',
                text: "Simpan kursus profile ini!",
                type: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Iya, simpan!'
            }).then(function(result) {
                if (result.value) {
					var subject = $('#subject').val();
					var rate = $('#rate').val();
					var language = $('#language').val();
					var level = ($('#sd').is(':checked') ? '#'+ 'sd' : '') + 
								($('#smp').is(':checked') ? '#'+ 'smp' : '') + 
								($('#sma').is(':checked') ? '#'+ 'sma' : '') + 
								($('#kuliah').is(':checked') ? '#'+ 'kuliah' : '') + 
								($('#umum').is(':checked') ? '#'+ 'umum' : '');
					
					var data = {
						'subject': subject,
						'rate': rate,
						'language': language,
						'level': level
					}
					$.ajax({
						url: "/course/save",
						type: "POST",
						contentType: "application/json",
						data: JSON.stringify(data),
						success: function(response) {
							if(response.status == 0){
								swal.fire(
			                        'Berhasil!',
			                        response.message,
			                        'success'
		                    	)
							} else {
								swal.fire(
			                        'Error!',
			                        response.message,
			                        'error'
			                    )
							}
							
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
		var setLevelCheckbox = function(level){
			if(level != null ){
				if(level.includes('sd')){
				$('#sd').attr('checked', 'checked');
				}
				if(level.includes('smp')){
					$('#smp').attr('checked', 'checked');
				}
				if(level.includes('sma')){
					$('#sma').attr('checked', 'checked');
				}
				if(level.includes('kuliah')){
					$('#kuliah').attr('checked', 'checked');
				}
				if(level.includes('umum')){
					$('#umum').attr('checked', 'checked');
				}
			}
			
		}
	}

	var selectProvince = function() {
		$.ajax({
			url: "/userprofile/provinces/list",
			type: "GET",
			contentType: "application/json",
			success: function(response) {
				$.each(response, function(i, item) {

					var $option = $('<option value="' + item.id + '">' + item.name + '</option>');
					$('#provinsi').append($option);
				});

			},
			error: function(response) {
				console.log(response)
			}
		})
	}
	
	var changeProvince = function() {
		
		$('#provinsi').change(function(){
			console.log($(this).val());
			var provinceId = $(this).val();
			$.ajax({
				url: "/userprofile/regencies?provinceId=" + provinceId,
				type: "GET",
				contentType: "application/json",
				success: function(response) {
					$('#kota').find('option').remove();
					$('#kecamatan').find('option').remove();
					$.each(response, function(i, item) {
	
						var $option = $('<option value="' + item.id + '">' + item.name + '</option>');
						$('#kota').append($option);
					});
	
				},
				error: function(response) {
					console.log(response)
				}
			})
		})
		
	}
	
	var changeRegency = function() {
		
		$('#kota').change(function(){
			console.log($(this).val());
			var regencyId = $(this).val();
			$.ajax({
				url: "/userprofile/districts?regencyId=" + regencyId,
				type: "GET",
				contentType: "application/json",
				success: function(response) {
					$('#kecamatan').find('option').remove();
					$.each(response, function(i, item) {
	
						var $option = $('<option value="' + item.id + '">' + item.name + '</option>');
						$('#kecamatan').append($option);
					});
	
				},
				error: function(response) {
					console.log(response)
				}
			})
		})
		
	}

	function updateThumbnail(fileInput) {
		var file = fileInput.files[0];
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#foto_avatar').css("background-image", "url(" + e.target.result + ")");
			var encoded = reader.result;

			var data = {
				'foto': encoded
			}


			$.ajax({
				url: "/userprofile/foto/upload",
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify(data),
				success: function(response) {
					console.log(response)
				},
				error: function(response) {
					console.log(response)
				}
			})

		}
		reader.readAsDataURL(file);
	}
	
	var initAddress = function() {
		selectProvince();

		$.ajax({
			url: "/userprofile/address",
			type: "GET",
			contentType: "application/json",
			success: function(response) {

				console.log(response);
				

				if(response.data != null){
					var data = response.data;
					$('#kota').find('option').remove();
					$('#kecamatan').find('option').remove();
					$.each(data.regencies, function(i, item) {
	
						var $option = $('<option value="' + item.id + '">' + item.name + '</option>');
						$('#kota').append($option);
					});
	
					$('#kecamatan').find('option').remove();
					$.each(data.districts, function(i, item) {
	
						var $option = $('<option value="' + item.id + '">' + item.name + '</option>');
						$('#kecamatan').append($option);
					});
					
					$('#provinsi option[value='+data.provinceId+']').attr('selected', 'selected');
					$('#kota option[value='+data.regencyId+']').attr('selected', 'selected');
					$('#kecamatan option[value='+data.districtId+']').attr('selected', 'selected');
				}
				
				
				
			},
			error: function(response) {
				console.log(response)
			}
		})

	}
	return {
		init: function() {
			sayHello();
			eventMyProfile();
			
			initAddress();
			
			changeProvince();
			changeRegency();
		}
	}
}();

jQuery(document).ready(function() {
	myprofile.init();
});