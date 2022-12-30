/**
 * 
 */
 "use strict";
 
 var KTKnowledgeAcquisition = function() {
 	
 	var refreshTable = function () {
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
					//scrollY: '50vh',
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
 							targets: -2,
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
 						{
 							targets: -1,
 							title: "Actions",
 							orderable: false,
 							render: function(data, type, full, meta){
 								
 							return `
 							<a class="btn btn-sm btn-clean btn-icon btn-icon-md btn-ubah" data-id="`+full.idDataset+`"  title="Edit">
	                          <i class="la la-edit"></i>
	                        </a>
 							<a class="btn btn-sm btn-clean btn-icon btn-icon-md btn-hapus" data-id="`+full.idDataset+`" title="Hapus">
	                          <i class="la la-trash"></i>
	                        </a>`;
 							}
 							
 						},
 					]
 				});
 				
 				$('#kt_table_dataset tbody tr').on('click', '.btn-ubah', function(){

 					edit($(this).data("id"));
 				});
 				$('#kt_table_dataset tbody tr').on('click', '.btn-hapus', function(){

 					remove($(this).data("id"));
 				});
 			},
 			error: function(response){
 				alert('error');
 			}
 		});
 	}
 	
 	var edit = function(id) {
 		console.log('Edit: ' + id);
 	}
 	
 	
 	var remove = function(id) {
 		console.log('Remove: ' + id);
 	}

 
 	return {
 		init: function(){
 			refreshTable();
 		}
 	};
 }();
 
 jQuery(document).ready(function() {
    KTKnowledgeAcquisition.init();
});