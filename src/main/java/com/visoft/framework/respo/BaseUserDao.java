package com.visoft.framework.respo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.visoft.framework.entity.BaseUser;

public interface BaseUserDao extends JpaRepository<BaseUser, Long> {
	
	@Query("select a from BaseUser a where userId=?1 and isvalid = 0")
	public BaseUser findByUserId(long userId);
}
