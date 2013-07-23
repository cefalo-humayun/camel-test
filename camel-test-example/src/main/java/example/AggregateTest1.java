package example;

import aggregation.Aggregate1;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author humayun
 */
public class AggregateTest1 {

    public static void main(String args[]) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:C:\\Users\\humayun\\Desktop\\Camel\\data\\?noop=true")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                exchange.getIn().setHeader("id", 1);
                            }
                        })
                        .aggregate(header("id"), new Aggregate1()).completionTimeout(3000)
                        .to("direct:ab");

                from("direct:ab").log("Next - ${body}");
            }
        });

        //ProducerTemplate template = camelContext.createProducerTemplate();
        camelContext.start();
        //template.sendBody("direct:ab", "http://192.168.1.144:8080/webservice/assignments/3203");

        Thread.sleep(5000);
        camelContext.stop();
    }
}
