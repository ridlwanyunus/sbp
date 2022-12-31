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
@Table(name="dataset")
@Data
@Getter
@Setter
public class Dataset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_dataset")
	private Integer idDataset;
	
	@Column(name="keuangan")
	private String keuangan;
	
	@Column(name="sikap")
	private String sikap;
	
	@Column(name="komunikasi")
	private String komunikasi;
	
	@Column(name="kecerdasan")
	private String kecerdasan;
	
	@Column(name="humoris")
	private String humoris;
	
	@Column(name="idaman")
	private String idaman;
}
