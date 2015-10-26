package com.boka.insurance.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.boka.insurance.service.LoginService;


@Service
public class LoginServiceImpl implements LoginService  {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.boka.insurance.service.impl.LoginService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public Long login(String userName,String password){
		List<Map<String,Object>> result=jdbcTemplate.queryForList("select insur_id from base_insur where insur_username=? and insur_password=?",
				userName,password);
		if(CollectionUtils.isEmpty(result)){
			return Long.valueOf(0L);
		}
		return (Long)result.get(0).get("insur_id");		
	}
}
