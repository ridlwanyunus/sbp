package com.project.sbp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.project.sbp.model.Node;
import com.project.sbp.repostiory.NodeRepository;

@Service
public class NodeService {

	@Autowired
	public NodeRepository repo;
	
	public List<Node> findAll(){
		return repo.findAll();
	}
	
	public void save(String label) {
		Node node = new Node();
		node.setLabel(label);
		node.setUsed(0);
		repo.save(node);
	}
	
	public void truncate() {
		repo.truncate();
	}
	
	public Node findByLabel(String label) {
		return repo.findByLabel(label);
	}
	
	public void save(Node node) {
		repo.save(node);
	}
	
}
