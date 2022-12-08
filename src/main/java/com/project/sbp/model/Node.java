package com.project.sbp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "node", schema = "sbptestdb")
@Data
@Getter
@Setter
public class Node {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_node")
	private Integer idNode;
	
	@Column(name="node")
	private String node;
	
	@Column(name="nama_node")
	private String namaNode;
	
	@Column(name="entropy")
	private Double entropy;
	
}
