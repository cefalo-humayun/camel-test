package restlet;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author humayun
 */
public class RestletTest {
    public static void main(String args[]) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                String uri = "http://newspilot.cefalo.com.bd/webservice/articles/12";
                from(uri + "?authMethod=Basic&authUsername=cefalo&authPassword=cefalo")

                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.err.println("Here body - " + exchange.getIn().getBody(String.class));
                    }
                }).log("we are here");
            }
        });

        camelContext.start();
        Thread.sleep(3000);
        camelContext.stop();
    }
}
