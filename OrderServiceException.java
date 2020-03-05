package com.practice.DefaultErrorHandler;

public class OrderServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OrderServiceException(String s) {
        super(s);
	}
        public OrderServiceException(String s, Throwable throwable) {
            super(s, throwable);
        }

}
