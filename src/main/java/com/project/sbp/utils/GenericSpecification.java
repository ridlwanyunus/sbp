package com.project.sbp.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class GenericSpecification<T> implements Specification<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SearchCriteria> list;
	
	public GenericSpecification() {
		this.list = new ArrayList<SearchCriteria>();
	}
	
	public void add(SearchCriteria criteria) {
		list.add(criteria);
	}
	
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		for(SearchCriteria criteria: list) {
			if(criteria.getSearchOperation().equals(SearchOperation.EQUAL)) {
				predicates.add(criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue().toString()));
			}
		}
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

}
