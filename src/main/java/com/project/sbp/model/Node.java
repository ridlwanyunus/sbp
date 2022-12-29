package com.project.sbp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="node")
public class Node {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_node")
	private Integer idNode;
	
	@Column(name="label")
	private String label;
	
	@Column(name="used")
	private Integer used;
}
