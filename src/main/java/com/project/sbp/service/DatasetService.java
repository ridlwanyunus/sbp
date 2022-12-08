package com.project.sbp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sbp.model.Dataset;
import com.project.sbp.repostiory.DatasetRepository;
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
	
	public List<Dataset> findByNode(String feature, String node){
		GenericSpecification genericSpecification = new GenericSpecification<Dataset>();
		genericSpecification.add(new SearchCriteria(feature, node, SearchOperation.EQUAL));
		
		return repo.findAll(genericSpecification);
	}
}
