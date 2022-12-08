package com.project.sbp.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.sbp.model.Feature;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer>{

	
	
	
}
