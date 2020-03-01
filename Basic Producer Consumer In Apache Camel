package com.practice.ProducerConsumer;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProducerAndConsumer {

	public static void main(String[] args) throws Exception {
		
		
		CamelContext camelContext=new DefaultCamelContext();
		
		camelContext.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				
				from("direct:start")
				.to("seda:end");
			}
		});
		
		camelContext.start();
		
		ProducerTemplate producerTemplate=camelContext.createProducerTemplate();
		producerTemplate.sendBody("direct:start", "Hello");
		
		ConsumerTemplate consumerTemplate=camelContext.createConsumerTemplate();
		String message=consumerTemplate.receiveBody("seda:end", String.class);
		
		System.out.println(message);
	}
}
