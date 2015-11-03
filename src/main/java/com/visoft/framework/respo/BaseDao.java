package com.visoft.framework.respo;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseDao<T, ID extends Serializable> extends SimpleJpaRepository<T, Serializable>{
	private final EntityManager entityManager;
	public BaseDao(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
	}

}
