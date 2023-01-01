package com.project.sbp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InferensiRequest {
	private String keuangan;
	private String sikap;
	private String komunikasi;
	private String kecerdasan;
	private String humoris;
}
