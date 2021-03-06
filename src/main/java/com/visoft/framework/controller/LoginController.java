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
import com.visoft.framework.service.LoginService;

@Controller
@RequestMapping("user")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	AuthManager authManager;
	@RequestMapping(value = "/login")
	@ResponseBody
	public ResponseStatus<Long> createOrder(HttpServletRequest request,String userName,String password) {
		ResponseStatus<Long> response=new ResponseStatus<Long>();
		try{
			authManager.login(request, userName, password);
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
	
 
	@RequestMapping(value = "/test")
	@ResponseBody
	@Authentication
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
	
	@RequestMapping(value = "/query")
	@ResponseBody
	
	public ResponseStatus<Long> testUqery(String userName) {
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
