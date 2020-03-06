package camel;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;


public class FileTest extends CamelTestSupport{

	@Override
	public RouteBuilder createRouteBuilder() throws Exception
	{
		return new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				
				from("file://target/inbox?noop=true")
				.to("file://target/outbox");
			}
		};
	}
	@Test
	public void testMoveFile() throws Exception{
		
		template.sendBodyAndProperty("file://target/inbox", "Hello Sanjay", Exchange.FILE_NAME, "hello.txt");
		Thread.sleep(2000);
		File target=new File("target/outbox");
		File source=new File("target/inbox");
		assertEquals(source.listFiles().length, target.listFiles().length);
		}
}
