package com.project.sbp.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.sbp.model.Dataset;
import com.project.sbp.model.ResponseTemplate;
import com.project.sbp.service.DatasetService;
import com.project.sbp.service.ID3ApiService;

@RestController
@RequestMapping("example")
public class ExampleController {

	@Autowired
	private DatasetService datasetService;
	
	@Autowired
	private ID3ApiService id3Service;
	
	@GetMapping("dataset")
	public List<Dataset> findAllDataset(){
		return datasetService.findAll();
	}
	
	@GetMapping("decision/tree")
	public ResponseTemplate decisionTree(){
		return id3Service.createDecisionTree();
	}
	
	@GetMapping("visualize/tree")
	public ResponseTemplate visualizeTree(){
		return id3Service.visualizeTreeDate();
	}
	
}
