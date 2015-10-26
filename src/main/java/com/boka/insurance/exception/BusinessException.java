package com.boka.insurance.exception;

public class BusinessException extends RuntimeException{
	
	/***/
	
	private static final long serialVersionUID = 1L;
	public int code;
	
	public BusinessException(int code,String msg){
		super(msg);
		this.code=code;
	};
}
