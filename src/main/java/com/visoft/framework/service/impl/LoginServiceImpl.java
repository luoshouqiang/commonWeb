package com.visoft.framework.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visoft.framework.repository.BaseUserDao;
import com.visoft.framework.service.LoginService;


@Service
public class LoginServiceImpl implements LoginService  {
	@Autowired
	private BaseUserDao userDao;
	
	/* (non-Javadoc)
	 * @see com.boka.insurance.service.impl.LoginService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public Long login(String userName,String password){
		return userDao.count();
	}
}
