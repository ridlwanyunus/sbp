package com.project.sbp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.sbp.dto.DatasetRequest;
import com.project.sbp.model.Dataset;
import com.project.sbp.model.ResponseTemplate;
import com.project.sbp.repostiory.DatasetRepository;
import com.project.sbp.utils.AppConstant;
import com.project.sbp.utils.GenericSpecification;
import com.project.sbp.utils.SearchCriteria;
import com.project.sbp.utils.SearchOperation;

@Service
public class DatasetService {

	@Autowired
	private DatasetRepository repo;
	
	public List<Dataset> findAll(){
		return repo.findAll();
	}
	
	public ResponseTemplate save(DatasetRequest request) {
		
		ResponseTemplate response = new ResponseTemplate();
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			Dataset dataset = mapper.convertValue(request, Dataset.class);
			repo.save(dataset);
			
			response.setCode(AppConstant.CODE_SUCCESS);
			response.setStatus(AppConstant.STATUS_SUCCESS);
			response.setMessage("Successfully added new record");
		} catch (Exception e) {
			response.setCode(AppConstant.CODE_ERROR);
			response.setStatus(AppConstant.STATUS_ERROR);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
}
