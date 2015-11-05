package com.visoft.framework.repository.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.visoft.framework.repository.BaseRepository;

public class BaseRspositoryImpl<M, ID extends Serializable> extends SimpleJpaRepository<M, ID> implements
		BaseRepository<M, ID> {
	private EntityManager em;

	public BaseRspositoryImpl(Class<M> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
	}

}
