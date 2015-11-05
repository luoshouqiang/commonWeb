package com.visoft.framework.repository;

import org.springframework.data.jpa.repository.Query;

import com.visoft.framework.entity.BaseUser;

public interface BaseUserDao extends BaseRepository<BaseUser, Long> {
	
	@Query("select a from BaseUser a where userId=?1 and isvalid = 0")
	public BaseUser findByUserId(long userId);
}
