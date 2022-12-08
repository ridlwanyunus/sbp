package com.project.sbp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sbp.model.Tree;
import com.project.sbp.repostiory.TreeRepository;

@Service
public class TreeService {

	@Autowired
	private TreeRepository repo;
	
	public List<Tree> findAll(){
		return repo.findAll();
	}
	
	public List<Tree> findByNode(String node){
		return repo.findByNode(node);
	}
}
