package com.visoft.framework.auth;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils.MethodFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethodSelector;

public class AnnotationMethodRequest implements BeanFactoryAware {
	
	private DefaultListableBeanFactory beanFactory;

	private static Set<String> authUrlSet=new HashSet<String>();
	
	public boolean isNeedAuthMethod(String url){
		findAnnoationBean();
		return checkAuthUrl(url);
	}
	
	private boolean checkAuthUrl(String url){
		if(CollectionUtils.isEmpty(authUrlSet)){
			return false;
		}
		for(String needAuthUrl:authUrlSet){
			if(url.startsWith(needAuthUrl)||
					url.equals(needAuthUrl)){
				return true;
			}
		}
		return false;
	}
	private void findAnnoationBean() {
		String[] beanNames = beanFactory.getBeanNamesForType(Object.class);
		for (String beanName : beanNames) {
			if (isHandler(beanFactory.getType(beanName))) {
				detectHandlerMethods(beanName);
			}
		}
	}
	
	

	private void detectHandlerMethods(String handler) {

		Class<?> handlerType = (handler instanceof String) ? beanFactory.getType((String) handler) : handler.getClass();

		final Class<?> userType = ClassUtils.getUserClass(handlerType);
		RequestMapping requestMaping=userType.getAnnotation(RequestMapping.class);
		String baseRequestUrl="";
		if(requestMaping!=null){
			baseRequestUrl=normalize(requestMaping.value()[0]);
		}
		
		Authentication authentication=userType.getAnnotation(Authentication.class);
		if(authentication!=null){
			authUrlSet.add(baseRequestUrl);
		}

		Set<Method> methods = HandlerMethodSelector.selectMethods(userType, new MethodFilter() {
			@Override
			public boolean matches(Method method) {
			return	 getMappingForMethod(method, Authentication.class) != null;
						
			}
		});
		for(Method m:methods){
			RequestMapping requestMapping=m.getAnnotation(RequestMapping.class);
		   String[] requestUrls=requestMapping.value();
		     for(String requestUrl:requestUrls){
		    	 authUrlSet.add(baseRequestUrl+normalize(requestUrl));
		     }
		}
	}

	private String normalize(String url){
		if(StringUtils.isEmpty(url)){
			return "";
		}
		if(!url.startsWith("/")){
			return "/"+url;
		}
		return url;
	}
	public  <A extends Annotation> A  getMappingForMethod(Method method, Class<A> userType) {
		return  AnnotationUtils.findAnnotation(method, userType);
	}


	protected boolean isHandler(Class<?> beanType) {
		return ((AnnotationUtils.findAnnotation(beanType, Controller.class) != null) || (AnnotationUtils
				.findAnnotation(beanType, RequestMapping.class) != null));
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (DefaultListableBeanFactory) beanFactory;

	}
}
