package com.project.sbp.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.sbp.model.Dataset;
import com.project.sbp.model.Feature;
import com.project.sbp.model.Node;
import com.project.sbp.model.Tree;
import com.project.sbp.service.DatasetService;
import com.project.sbp.service.FeatureService;
import com.project.sbp.service.NodeService;
import com.project.sbp.service.TreeService;
import com.project.sbp.utils.ID3Utils;

@RestController
@RequestMapping("example")
public class ExampleController {

	@Autowired
	private NodeService nodeService;
	
	@Autowired
	private DatasetService datasetService;
	
	@Autowired
	private FeatureService featureService;
	
	@Autowired
	private TreeService treeService;
	
	@Autowired
	private ID3Utils id3utils;
	
	
	@GetMapping("list/node")
	public List<Node> listNode(){
		return nodeService.findAll();
	}
	
	@GetMapping("list/dataset")
	public List<Dataset> listDataset(){
		return datasetService.findAll();
	}
	
	@GetMapping("entropy")
	public Map<String, Object> entropy(@RequestParam("node") String keyword){
		List<Dataset> currentDataset = datasetService.findAll();
		
		Node node = nodeService.findClassifier(keyword);
		String kodeNode = node.getNode();
		
		Double entropy = id3utils.calculateEntropy(currentDataset, kodeNode);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("node", keyword);
		map.put("entropy", entropy);
		return map;
	}
	
	@GetMapping("entropy/all")
	public Map<String, Object> entropyAll(){
		
		List<Feature> features = featureService.findAll();
		List<Dataset> currentDataset = datasetService.findAll();
		Map<String, Object> map = new HashMap<String, Object>();
		
		for(Feature feature: features) {
			List<Tree> trees = treeService.findByNode(feature.getNode());
			Map<String, Object> featureMap = new HashMap<String, Object>();
			
			for(Tree tree: trees) {
				Double entropy = id3utils.calculateEntropy(currentDataset, tree.getChild());
				featureMap.put(tree.getChild(), entropy);
			}
			map.put(feature.getNode(), featureMap);
		}

		return map;
	}
	
	
}
