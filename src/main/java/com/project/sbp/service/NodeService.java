package com.project.sbp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sbp.model.Node;
import com.project.sbp.repostiory.NodeRepository;

@Service
public class NodeService {

	@Autowired
	private NodeRepository repo;
	
	public List<Node> findAll(){
		return repo.findAll();
	}
	
	public Node findClassifier(String namaNode){
		return repo.findClassifier(namaNode);
	}
	
	
}
