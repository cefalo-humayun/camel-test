package restlet;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * @author humayun
 */
public class MyTestRoute extends RouteBuilder {
    private final String uri;

    public MyTestRoute() {
        this.uri = "http://camel.apache.org/restlet.html";
    }
    @Override
    public void configure() throws Exception {
        from( uri)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.err.println("Body - " + exchange.getIn().getBody(String.class));
                    }
                })
                .log("Body ${body}");
    }
}
