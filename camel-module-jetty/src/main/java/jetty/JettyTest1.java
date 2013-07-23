package jetty;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author humayun
 */
public class JettyTest1 {
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("http://newspilot.cefalo.com.bd/webservice/articles/12?authMethod=Basic&authUsername=cefalo&authPassword=cefalo")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                System.err.println(exchange.getIn().getBody(String.class));
                            }
                        }).log("Here body - ${body}");
            }
        });

        ProducerTemplate producerTemplate = context.createProducerTemplate();
        context.start();
        //producerTemplate.sendBody("direct:a", "this is body");

        Thread.sleep(3000);
        context.stop();

    }
}
