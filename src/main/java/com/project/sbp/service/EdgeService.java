package com.project.sbp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sbp.model.Edge;
import com.project.sbp.repostiory.EdgeRepository;

@Service
public class EdgeService {

	@Autowired
	private EdgeRepository repo;
	
	public List<Edge> findAll(){
		return repo.findAll();
	}
	
	
	public void save(Integer node, Integer child) {
		Edge edge = new Edge();
		edge.setNode(node);
		edge.setChild(child);
		
		repo.save(edge);
	}
	
	public void truncate() {
		repo.truncate();
	}
}
