package com.project.sbp.repostiory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.sbp.model.Node;

@Repository
public interface NodeRepository extends JpaRepository<Node, Integer> {

	@Query("select n from Node n")
	public List<Node> findAll();
	
	@Query("select n from Node n where n.namaNode = :namaNode")
	public Node findClassifier(@Param("namaNode") String namaNode);
	
	
}
