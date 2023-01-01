package com.project.sbp.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.sbp.dto.InferensiRequest;
import com.project.sbp.model.ResponseTemplate;
import com.project.sbp.service.InferensiService;

@RestController
@RequestMapping("predict")
public class InferensiController {

	@Autowired
	private InferensiService service;
	
	@PostMapping("")
	public ResponseTemplate prediction(@RequestBody InferensiRequest request) {
		return service.predict(request);
	}
	
}
