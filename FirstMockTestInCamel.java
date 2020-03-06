package camel;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class FirstMockTest extends CamelTestSupport {

	@Override
	public RouteBuilder createRouteBuilder() throws Exception
	{
		return new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				
				from("file://target/inbox?noop=true")
				.to("mock:quote");
			}
		};
	}
	@Test
	public void testMoveFile() throws Exception{
		
		MockEndpoint quote=getMockEndpoint("mock:quote");
		quote.expectedMessageCount(1);
		
		template.sendBody("file://target/inbox", "Hello Sanjay");
		quote.assertIsSatisfied();
		
		}
}
