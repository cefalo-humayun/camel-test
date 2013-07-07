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
                from("http://newspilot.cefalo.com.bd/webservice/articles/12?authMethod=Basic&authUsername=cefalo&authPassword=cefalo")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                Message message = exchange.getIn();
                                System.out.println("My body - " + message.getBody(String.class));
                                String body = message.getBody(String.class);
                                message.setBody(body);
                                message.setHeader("myHeaderName", "HumayunKabir");
                            }
                        })
                        .beanRef("myBean");
            }
        });

        ProducerTemplate template = camelContext.createProducerTemplate();
        camelContext.start();
        //template.sendBody("direct:ab", "Humayun");

        Thread.sleep(5000);
        camelContext.stop();
    }
}