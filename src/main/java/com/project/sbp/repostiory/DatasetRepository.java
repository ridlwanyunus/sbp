package com.project.sbp.repostiory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.sbp.model.Dataset;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, Integer>, JpaSpecificationExecutor<Dataset> {

	@Query("select d from Dataset d where d.outlook = :node")
	public List<Dataset> findByOutlook(@Param("node") String node);
	
	@Query("select d from Dataset d where d.temperature = :node")
	public List<Dataset> findByTemperature(@Param("node") String node);
	
	@Query("select d from Dataset d where d.humidity = :node")
	public List<Dataset> findByHumidity(@Param("node") String node);
	
	@Query("select d from Dataset d where d.wind = :node")
	public List<Dataset> findByWind(@Param("node") String node);
	
}
