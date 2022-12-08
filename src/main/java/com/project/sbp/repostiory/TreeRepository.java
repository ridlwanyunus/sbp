package com.project.sbp.repostiory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.sbp.model.Tree;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Integer> {

	@Query("select t from Tree t where t.node = :node")
	public List<Tree> findByNode(@Param("node") String node);
	
	@Query("select t from Tree t where t.child = :child")
	public Tree findFeatureByNode(@Param("child") String child);
	
}
