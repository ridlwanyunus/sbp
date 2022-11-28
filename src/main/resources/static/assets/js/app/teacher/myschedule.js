/**
 * 
 */

"use strict";

var myschedule = function() {

	var initDatatables = function(){
		
		
		
		$.ajax({
			url: "/schedule/find",
			type: "GET",
			contentType: "application/json",
			success: function(response) {
				
				var table = $('#kt_table_1');
				console.log(response);
				// begin first table
				
				table.DataTable({
					data: response,
					destroy: true,
					scrollY: '50vh',
					scrollX: true,
					scrollCollapse: true,
					ordering: false,
					columns: [
						{
							"data": 'paymentRefference'
						},
						{
							"data": 'fullname'
						},
						{
							"data": 'telephone'
						},
						{
							"data": 'email'
						},
						{
							"data": 'date'
						},
						{
							"data": 'time'
						},
						{
							"data": 'duration'
						},
						{
							targets: -2,
							title: 'Status',
							orderable: false,
							render: function(data, type, full, meta) {
								if(full.status == 0){
									return '<span class="kt-badge kt-badge--warning kt-badge--inline kt-badge--pill">Proses</span>';
								} else 
								if(full.status == 1)
								return '<span class="kt-badge kt-badge--success kt-badge--inline kt-badge--pill">Selesai</span>';
								else 
									if(full.status == -1)
								return '<span class="kt-badge kt-badge--danger kt-badge--inline kt-badge--pill">Dibatalkan</span>';
							}
						},
						{
							targets: -1,
							title: 'Actions',
							orderable: false,
							render: function(data, type, full, meta) {
								if(full.status == 0){
									return `
			                        <div class="kt-section__content kt-section__content--solid">
										<button type="button" data-id="`+full.paymentRefference+`" data-status="1" class="btn btn-sm btn-outline-success">Selesai</button>
										<button type="button" data-id="`+full.paymentRefference+`" data-status="-1" class="btn btn-sm btn-outline-danger">Batalkan</button>
									</div>
			                        `
								} else {
									return ``;
								}
								;
							},
						}
					],
				});			
				$('.btn-outline-success').click(function(e){
					var id = $(this).data("id");
					var status = $(this).data("status");
					console.log(id);
					console.log(status);
				});
			},
			error: function(response) {
				console.log(response)
			}
		});
	}
	var buttonHandle = function(){
		
	}
	
	
	return {
		init: function() {
			initDatatables();
			buttonHandle();
		}
	}
}();

jQuery(document).ready(function() {
	myschedule.init();
});