package com.shadesh;

import beans.MyBean;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

/**
 * @author humayun
 */
public class CamelTest1 {
    public static void main(String args[]) throws Exception {
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("myBean", new MyBean());

        CamelContext camelContext = new DefaultCamelContext(registry);
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:ab")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                Message message = exchange.getIn();
                                System.out.println("My body - " + message.getBody().toString());
                            }
                        })
                        .beanRef("myBean");
            }
        });

        ProducerTemplate template = camelContext.createProducerTemplate();
        camelContext.start();
        template.sendBody("direct:ab", "Humayun");

        Thread.sleep(5000);
        camelContext.stop();
    }
}