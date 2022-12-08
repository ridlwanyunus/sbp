package com.project.sbp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sbp.model.Dataset;
import com.project.sbp.repostiory.DatasetRepository;

@Service
public class DatasetService {

	@Autowired
	private DatasetRepository repo;
	
	public List<Dataset> findAll(){
		return repo.findAll();
	}
	
	
}
