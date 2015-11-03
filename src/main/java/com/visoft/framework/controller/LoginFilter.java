package com.visoft.framework.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginFilter implements Filter{
    
    private static final Logger LOG = LoggerFactory.getLogger(LoginFilter.class);
//    
//    @Autowired
//    private UserLoginService    userLoginService;
    


	public void destroy() {
    }
    
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest servletRequest=(HttpServletRequest)request;
        HttpServletResponse servletResponse=(HttpServletResponse)response;
    	String servletPath = servletRequest.getServletPath().toLowerCase();
    	
    	
    	
    	if (servletPath.endsWith("login.html")||servletPath.endsWith(".css")||servletPath.endsWith("/login")
    			||servletPath.endsWith(".js")||servletPath.endsWith(".png")||servletPath.endsWith(".jpg") 
    			) {
    		chain.doFilter(request, response);
            return ;
    	}
    	
        HttpSession session=servletRequest.getSession(false);
         if(session==null){
        	 servletResponse.sendRedirect(((HttpServletRequest)request).getContextPath() + "/resources/html/login.html");         
         }else{
        	 chain.doFilter(request, response);
         }
        
    }

    public void init(FilterConfig arg0) throws ServletException {
    }
}