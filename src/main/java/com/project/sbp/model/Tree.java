package com.project.sbp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tree", schema = "sbptestdb")
@Data
public class Tree {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_tree")
	private Integer idTree;
	
	@Column(name="node")
	private String node;
	
	@Column(name="child")
	private String child;
	
	@Column(name="is_changeable")
	private Integer isChangeable;
	
}
