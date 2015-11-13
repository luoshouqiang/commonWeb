package com.visoft.framework.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.visoft.framework.auth.AuthManager;
import com.visoft.framework.auth.Authentication;
import com.visoft.framework.exception.BusinessException;
import com.visoft.framework.exception.ResponseStatus;
import com.visoft.framework.exception.ResponseStatus.ErrorCode;

@Controller
@RequestMapping("test")
@Authentication
public class TestController {
	
	
	@Autowired
	AuthManager authManager;
	@RequestMapping(value = "/auth")
	@ResponseBody
	public ResponseStatus<Long> doGet(HttpServletRequest request,String userName,String password) {
		ResponseStatus<Long> response=new ResponseStatus<Long>();
		
		return response;
	}
	
 
	@RequestMapping(value = "/auth2")
	@ResponseBody
	public ResponseStatus<Long> testOrder(String userName) {
		ResponseStatus<Long> response=new ResponseStatus<Long>();
		try{
			System.out.println("--------in test");
		}catch(BusinessException ex){
			response=new ResponseStatus<Long>(ex);
			response.setData(0L);
		}catch(Exception ex){
			response.setData(0L);
			ex.printStackTrace();
			response=new ResponseStatus<Long>();
			response.setCode(ErrorCode.SYS_ERROR.getCode());
			response.setMsg("系统异常");
		}
		return response;
	}
	

	
}
