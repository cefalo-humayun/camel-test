package com.cefalo.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * @author Humayun
 */
public class MyRouteBuilder extends RouteBuilder {

    public void configure() {
        /*// populate the message queue with some messages
        from("file:src/data?noop=true").to("jms:test.MyQueue");

        from("jms:test.MyQueue").to("file://target/routeOutput");

        // set up a listener on the file component
        from("file://target/routeOutput?noop=true").beanRef("myBean");*/
        from("direct:ab")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.err.println("In Process : - " + exchange.getIn().getBody(String.class));
                    }
                })
                .to("file:/home/sky/Desktop/camel/data/");
    }

}