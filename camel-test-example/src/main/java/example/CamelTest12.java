package example;

import aggregation.AggregationStrategy1;
import aggregation.AggregationStrategy2;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.processor.aggregate.GroupedExchangeAggregationStrategy;

/**
 * @author humayun
 */
public class CamelTest12 {

    public static void main(String args[]) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                /*from("direct:ab").process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.err.println("Body - " + exchange.getIn().getBody(String.class));
                    }
                });*/
                from("http://camel.apache.org/enterprise-integration-patterns.html")
                        .log("${body}")
                        .enrich("direct:serviceFacade").setBody(property(Exchange.GROUPED_EXCHANGE));

                from("direct:serviceFacade")
                        .multicast(new GroupedExchangeAggregationStrategy()).parallelProcessing()
                        .log("Log here ${body}")
                        .enrich("http://google.com?q=Foo", new AggregationStrategy1())
                        .enrich("http://google.com?q=Bar", new AggregationStrategy2())
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {

                                System.err.println("Body - \n" + exchange.getIn().getBody(String.class) );
                            }
                        })
                        .end();
            }
        });

        //ProducerTemplate template = camelContext.createProducerTemplate();
        camelContext.start();
        //template.sendBody("direct:ab", "http://192.168.1.144:8080/webservice/assignments/3203");

        Thread.sleep(5000);
        camelContext.stop();
    }
}
