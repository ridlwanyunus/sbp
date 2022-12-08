package com.project.sbp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="feature", schema = "sbptestdb")
@Data
public class Feature {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_feature")
	private Integer idFeature;
	
	@Column(name="node")
	private String node;
	
	@Column(name="is_used")
	private Integer isUsed;
	
}
