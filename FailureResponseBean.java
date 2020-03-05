package com.practice.DefaultErrorHandler;

import org.apache.camel.Handler;

public class FailureResponseBean {

	@Handler
	public String failure(String body,Exception e)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("ERROR: ");
		sb.append(e.getMessage());
		sb.append("\nBODY: ");
		sb.append(body);
		System.out.println(sb.toString());
		return sb.toString();
	}
}
