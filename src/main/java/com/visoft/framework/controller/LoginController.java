package com.visoft.framework.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.visoft.framework.auth.AuthManager;
import com.visoft.framework.exception.BusinessException;
import com.visoft.framework.exception.ResponseStatus;
import com.visoft.framework.exception.ResponseStatus.ErrorCode;
import com.visoft.framework.service.LoginService;

@Controller
@RequestMapping("user")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	@RequestMapping(value = "/login")
	@ResponseBody
	public ResponseStatus<Long> createOrder(HttpServletRequest request,String userName,String password) {
		ResponseStatus<Long> response=new ResponseStatus<Long>();
		try{
			AuthManager.login(request, userName, password);
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
	public static void main(String[] args) {
		try {
			String resource=new DefaultResourceLoader().getResource("1.txt").getFile().getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
