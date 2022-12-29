package com.project.sbp.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.project.sbp.model.Edge;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, Integer> {

	@Modifying
	@Query(value="truncate table edge", nativeQuery = true)
	public void truncate();
	
	
	
}
