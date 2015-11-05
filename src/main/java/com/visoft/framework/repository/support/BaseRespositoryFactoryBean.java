package com.visoft.framework.repository.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.visoft.framework.repository.BaseRepository;

public class BaseRespositoryFactoryBean<R extends JpaRepository<M, ID>, M, ID extends Serializable> extends
		JpaRepositoryFactoryBean<R, M, ID> {

	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new BaseRepositoryFactory(entityManager);
	}

	private class BaseRepositoryFactory<M, ID extends Serializable> extends JpaRepositoryFactory {
		private EntityManager entityManager;

		public BaseRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		protected Object getTargetRepository(RepositoryMetadata metadata) {
			if (BaseRepository.class.isAssignableFrom(metadata.getRepositoryInterface())) {
				return new BaseRspositoryImpl((Class<M>) metadata.getDomainType(), entityManager);
			}
			return super.getTargetRepository(metadata, entityManager);

		}

		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			if (BaseRepository.class.isAssignableFrom(metadata.getRepositoryInterface())) {
				return BaseRepository.class;
			}
			return super.getRepositoryBaseClass(metadata);

		}

	}
}
