

package com.practice.camelFileCopy;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileCopy {

	public static void main(String[] args) throws Exception {
		
		CamelContext camelContext=new DefaultCamelContext();
		
		camelContext.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				
				from("file:data/input?noop=true")  // noop=true for copy/paste else cut/paste.
				.to("file:data/output");
				
					
			}
		});
		
		while(true)  //for infinite time.
		{
			camelContext.start();
		}
	}
}
