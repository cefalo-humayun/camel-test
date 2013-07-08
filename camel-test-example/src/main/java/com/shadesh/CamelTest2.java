package com.shadesh;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author Humayun
 */
public class CamelTest2 {
    public static void main(String args[]) throws Exception {

        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                //from("jetty://http://www.google.com.bd").to("file:/home/sky/Desktop/camel/data/");
            }
        });

        ProducerTemplate template = camelContext.createProducerTemplate();
        camelContext.start();
        //template.sendBody("direct:ab", "http://192.168.1.144:8080/webservice/assignments/3181");
        Thread.sleep(2000);
        camelContext.stop();
    }
}
