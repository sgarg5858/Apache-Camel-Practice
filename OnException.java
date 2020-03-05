package com.practice.DefaultErrorHandler;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.LoggingLevel;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class DefaultErrorHandler {

	public static void main(String[] args) throws Exception {
		
		OrderService orderService=new OrderService();
		SimpleRegistry registry=new SimpleRegistry();
		registry.put("orderService", orderService);
		
		CamelContext camelContext=new DefaultCamelContext(registry);
		
		camelContext.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				
				//context scope
				errorHandler(defaultErrorHandler()
						.maximumRedeliveries(5)
						.redeliveryDelay(1l)
						.retryAttemptedLogLevel(LoggingLevel.WARN));
				
				onException(OrderServiceException.class).maximumRedeliveries(8);

				//route scope
				from("direct:start")
				.bean(OrderService.class, "validate")
				.bean(OrderService.class,"check")
				.to("seda:end");
				
				
			}
		});
		camelContext.start();
		ProducerTemplate producerTemplate=camelContext.createProducerTemplate();
		producerTemplate.sendBody("direct:start","ActiveMQ in Action");
		
		ConsumerTemplate consumerTemplate=camelContext.createConsumerTemplate();
		String message=consumerTemplate.receiveBody("seda:end", String.class);
		System.out.println(message);
	}
}
