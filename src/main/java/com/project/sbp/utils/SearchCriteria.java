package com.project.sbp.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
	
	private String key;
	private Object value;
	private SearchOperation searchOperation;
	
}
