package com.project.sbp.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.sbp.model.Node;

@Repository
public interface NodeRepository extends JpaRepository<Node, Integer> {

	@Modifying
	@Query(value="truncate table node", nativeQuery = true)
	public void truncate();

	@Query(value="select * from node where label = :label and used = 0 limit 1 ", nativeQuery = true)
	public Node findByLabel(@Param("label") String label);
	
}
