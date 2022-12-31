package com.project.sbp.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.sbp.dto.DatasetRequest;
import com.project.sbp.model.ResponseTemplate;
import com.project.sbp.service.DatasetService;

@RestController
@RequestMapping("dataset")
public class DatasetController {

	@Autowired
	private DatasetService dataService;
	
	@PostMapping("save")
	public ResponseTemplate save(@RequestBody DatasetRequest request) {
		ResponseTemplate response = dataService.save(request);
		return response;
	}
	
	
}
