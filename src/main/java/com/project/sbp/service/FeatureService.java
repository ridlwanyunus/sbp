package com.project.sbp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sbp.model.Feature;
import com.project.sbp.repostiory.FeatureRepository;

@Service
public class FeatureService {

	@Autowired
	private FeatureRepository repo;
	
	public List<Feature> findAll(){
		return repo.findAll();
	}
	
	
}
