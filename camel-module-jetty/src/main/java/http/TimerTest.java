package http;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author humayun
 */
public class TimerTest {

    public static void main(String args[]) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("timer://foo?fixedRate=true&period=300")
                        .log("print message.....");
                        //.to("bean:myBean?method=someMethodName");
            }
        });

        camelContext.start();

        Thread.sleep(30000);
        camelContext.stop();
    }
}
