package com.visoft.framework.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.visoft.framework.exception.BusinessException;
import com.visoft.framework.exception.ResponseStatus;
import com.visoft.framework.exception.ResponseStatus.ErrorCode;
import com.visoft.framework.service.LoginService;

@Controller
@RequestMapping("user")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseStatus<Long> createOrder(HttpSession session,String userName,String password) {
		ResponseStatus<Long> response=new ResponseStatus<Long>();
		try{
			long companyId=loginService.login(userName, password);
			session.setAttribute("companyId", companyId);
			response.setData(companyId);
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
