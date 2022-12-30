/**
 * 
 */
 "use strict";
 
 var KTKnowledgeRepresentation = function() {
 	
 	var welcome = function(){
 		console.log('Welcome to KTKnowledgeRepresentation Page');
 		
 		$.ajax({
 			url: "example/visualize/tree",
 			type: "GET",
 			contentType: "application/json",
 			success: function(response){
 				console.log(response);
 				
 				var nodes = response.data.nodes;
				
				var edges = response.data.edges;
				
				// create a network
				var container = document.getElementById("tree");
				var data = {
				  nodes: new vis.DataSet(nodes),
				  edges: new vis.DataSet(edges),
				};
				var options = {
					height: '480px',
				  layout: {
				    hierarchical: {
				      direction: "UD",
				      sortMethod: "directed",
				    },
				  },
				  edges: {
				    arrows: "to",
				  },
				};
				network = new vis.Network(container, data, options);
				
				// periodically change the layout
				let i = 0;
				setInterval(() => {
				  var leaves = data.nodes.get().filter((node) =>
				    network
				      .getConnectedEdges(node.id)
				      .map((edgeId) => data.edges.get(edgeId))
				      .every((edge) => edge.to === node.id)
				  );
				  var leaf = leaves[i++ % leaves.length];
				
				  var edgeIds = network.getConnectedEdges(leaf.id);
				  var edge = data.edges.get(edgeIds[i++ % edgeIds.length]);
				  var oldParent = data.nodes.get(edge.from);
				
				  while (
				    (i % data.nodes.length) + 1 === leaf.id ||
				    (i % data.nodes.length) + 1 === oldParent.id
				  ) {
				    ++i;
				  }
				  var newParent = data.nodes.get((i++ % data.nodes.length) + 1);
				
				  data.edges.update({
				    id: edge.id,
				    from: newParent.id,
				  });
				
				  console.info(
				    `Node ${leaf.id} was reconnected from it's former parent node ${oldParent.id} to node ${newParent.id}.`
				  );
				}, 1000);
 			},
 			error: function(response){
 				console.log("error");
 			}
 		});
 		
		
 		
 	}
 	
 	var buttonHandler = function(){
		$('#btn-calculate').on('click', function(e){
			console.log('recalculate');
			$.ajax({
	 			url: "example/decision/tree",
	 			type: "GET",
	 			contentType: "application/json",
	 			success: function(response){
	 				swal.fire("Success!", "Success Recalculate Graph", "success");
	 			},
	 			error: function(response){
		 			swal.fire("Error!", "Error Recalculate Graph!", "error");
	 			},
	 			complete: function(response) {
	 				welcome();
	 			}
 			});
		});	
	}

 	return {
 		init: function(){
 			welcome();
 			buttonHandler();
 		}
 	};
 }();
 
 jQuery(document).ready(function() {
    KTKnowledgeRepresentation.init();
});