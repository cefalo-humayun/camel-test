package http;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author humayun
 */
public class HTTP4Test {

    public static void main(String args[]) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:ab")
                        .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                        .setHeader(Exchange.HTTP_URI, constant("http://newspilot.cefalo.com.bd/webservice/articles/12"))
                        .to("http4://dummyhost?authUsername=cefalo&authPassword=cefalo")
                        .log("Body is : ${body}");
            }
        });

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        camelContext.start();
        producerTemplate.sendBody("direct:ab", "http://newspilot.cefalo.com.bd/webservice/articles/12");

        Thread.sleep(3000);
        camelContext.stop();
    }
}
