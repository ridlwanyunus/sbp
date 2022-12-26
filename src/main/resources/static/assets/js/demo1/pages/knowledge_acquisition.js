/**
 * 
 */
 "use strict";
 
 var KTKnowledgeAcquisition = function() {
 	
 	var welcome = function () {
 		console.log('Welcome to KTKnowledgeAcquisition Page');
 		$.ajax({
 			url: "/example/dataset",
 			type: "GET",
 			contentType: "application/json",
 			success: function(response){
 				console.log(response);
 				var table = $('#kt_table_dataset');
 				table.DataTable({
 					data: response,
					destroy: true,
					scrollY: '50vh',
					scrollX: true,
					scrollCollapse: true,
					ordering: false,
 					columns: [
 						{"data": "idDataset"},
 						{"data": "keuangan"},
 						{"data": "sikap"},
 						{"data": "komunikasi"},
 						{"data": "kecerdasan"},
 						{"data": "humoris"},
 						{
 							targets: -1,
 							title: "Idaman",
 							orderable: false,
 							render: function(data, type, full, meta){
 								var badge = '';
 								if(full.idaman == 'Ya'){
 									badge = 'kt-badge--success';
 								} else 
 								if(full.idaman == 'Tidak'){
 									badge = 'kt-badge--danger';
 								}
 								return '<span class="kt-badge '+badge+' kt-badge--inline kt-badge--pill">'+full.idaman+'</span>';
 							}
 							
 						},
 					]
 				});
 			},
 			error: function(response){
 				alert('error');
 			}
 		});
 	}
 	

 	var initTable1 = function () {
		var table = $('#kt_table_dataset');

		// begin first table
		table.DataTable({
			scrollY: '50vh',
			scrollX: true,
			scrollCollapse: true,
			columnDefs: [
				{
					targets: -1,
					title: 'Actions',
					orderable: false,
					render: function(data, type, full, meta) {
						return `
                        <span class="dropdown">
                            <a href="#" class="btn btn-sm btn-clean btn-icon btn-icon-md" data-toggle="dropdown" aria-expanded="true">
                              <i class="la la-ellipsis-h"></i>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="#"><i class="la la-edit"></i> Edit Details</a>
                                <a class="dropdown-item" href="#"><i class="la la-leaf"></i> Update Status</a>
                                <a class="dropdown-item" href="#"><i class="la la-print"></i> Generate Report</a>
                            </div>
                        </span>
                        <a href="#" class="btn btn-sm btn-clean btn-icon btn-icon-md" title="View">
                          <i class="la la-edit"></i>
                        </a>`;
					},
				},
				{
					targets: 8,
					render: function(data, type, full, meta) {
						var status = {
							1: {'title': 'Pending', 'class': 'kt-badge--brand'},
							2: {'title': 'Delivered', 'class': ' kt-badge--danger'},
							3: {'title': 'Canceled', 'class': ' kt-badge--primary'},
							4: {'title': 'Success', 'class': ' kt-badge--success'},
							5: {'title': 'Info', 'class': ' kt-badge--info'},
							6: {'title': 'Danger', 'class': ' kt-badge--danger'},
							7: {'title': 'Warning', 'class': ' kt-badge--warning'},
						};
						if (typeof status[data] === 'undefined') {
							return data;
						}
						return '<span class="kt-badge ' + status[data].class + ' kt-badge--inline kt-badge--pill">' + status[data].title + '</span>';
					},
				},
				{
					targets: 9,
					render: function(data, type, full, meta) {
						var status = {
							1: {'title': 'Online', 'state': 'danger'},
							2: {'title': 'Retail', 'state': 'primary'},
							3: {'title': 'Direct', 'state': 'success'},
						};
						if (typeof status[data] === 'undefined') {
							return data;
						}
						return '<span class="kt-badge kt-badge--' + status[data].state + ' kt-badge--dot"></span>&nbsp;' +
							'<span class="kt-font-bold kt-font-' + status[data].state + '">' + status[data].title + '</span>';
					},
				},
			],
		});
	};
 
 	return {
 		init: function(){
 			welcome();
// 			initTable1();
 		}
 	};
 }();
 
 jQuery(document).ready(function() {
    KTKnowledgeAcquisition.init();
});