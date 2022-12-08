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
@Table(name="dataset", schema = "sbptestdb")
@Data
@Getter
@Setter
public class Dataset {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_dataset")
	private Integer idDataset;
	
	@Column(name="outlook")
	private String outlook;
	
	@Column(name="temperature")
	private String temperature;
	
	@Column(name="humidity")
	private String humidity;
	
	@Column(name="wind")
	private String wind;
	
	@Column(name="tennis")
	private String tennis;
	
}
