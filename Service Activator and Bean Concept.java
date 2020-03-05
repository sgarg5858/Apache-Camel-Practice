package com.practice.CamelBeans;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class CallMethodUsingBeanComponent {

	public static void main(String[] args) throws Exception {
		
		MyService myService=new MyService();
		SimpleRegistry registry=new SimpleRegistry();
		registry.put("myService", myService);
		
       CamelContext camelContext=new DefaultCamelContext(registry);
		
		camelContext.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				
//				from("direct:start")
//				.to("bean:myService?method=DoSomething");
				
				from("direct:start")
				.bean(MyService.class,"bar")
				.to("seda:end");
				
			}
		});
		camelContext.start();
		ProducerTemplate producerTemplate=camelContext.createProducerTemplate();
		producerTemplate.sendBody("direct:start", "Hello Sanjay");
		
		ConsumerTemplate consumerTemplate=camelContext.createConsumerTemplate();
		String message=consumerTemplate.receiveBody("seda:end", String.class);
		
		System.out.println(message);
	}
}
***************************************************************************************

package com.practice.CamelBeans;

import org.apache.camel.Body;
import org.apache.camel.Handler;

public class MyService {

	@Handler
	public void echo(String message)
	{
		System.out.println(message);
	}
	
	public String bar(@Body String message)
	{
		message=message+(" Garg");
		return message;
	}
}
If no method is specified then @Hander method will execute and to make changes to body of exchange we have to return the value from method 
Like in bar method

Output:
Hello Sanjay Garg
