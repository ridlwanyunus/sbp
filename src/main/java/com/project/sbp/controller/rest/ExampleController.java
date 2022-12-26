package com.project.sbp.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.sbp.model.Dataset;
import com.project.sbp.service.DatasetService;

@RestController
@RequestMapping("example")
public class ExampleController {

	@Autowired
	private DatasetService datasetService;
	
	@GetMapping("dataset")
	public List<Dataset> findAllDataset(){
		return datasetService.findAll();
	}
	
}
