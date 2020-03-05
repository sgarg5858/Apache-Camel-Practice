package com.practice.DefaultErrorHandler;

import org.apache.camel.Body;

public class OrderService {

	public String validate(@Body String body) throws Exception
	{
		if(body.contains("amount"))
			throw new Exception("Invalid order");
		
		body=body+ ",id=123";
		return body;
	}
	
	public String enrich(@Body String body) throws Exception
	{
		if(body.contains("ActiveMQ in Action"))
			throw new Exception("ActiveMQ in Action is out of Stock");
		
		body=body+ ",status=OK";
		return body;
	}
}
