package com.project.sbp.dto;

import lombok.Data;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatasetRequest {

	private String keuangan;
	private String sikap;
	private String komunikasi;
	private String kecerdasan;
	private String humoris;
	private String idaman;
	
}
