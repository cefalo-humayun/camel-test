package http;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author humayun
 */
public class XPathTest {

    public static void main(String args[]) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        /*camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:ab")
                        .log("Here body is ${body}")
                        .setBody(simple("The body is changed"))
                        .log("The modified body - ${body}");
            }
        });*/

        camelContext.addRoutes(new MyTimerRoute());

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        camelContext.start();
        String commandText = "<command>\n" +
                "<type>Export</type>\n" +
                "<link href=\"http://newspilot.cefalo.com.bd/webservice/assignments/3203\" rel=\"assignment\" title=\"Export amitestkorchi-sky-54\"/>\n" +
                "<link href=\"http://newspilot.cefalo.com.bd/webservice/products/9\" rel=\"product\" title=\"Humayun\"/>\n" +
                "</command>";

        producerTemplate.sendBody("direct:ab", commandText);

        Thread.sleep(3000);
        camelContext.stop();
    }
}
