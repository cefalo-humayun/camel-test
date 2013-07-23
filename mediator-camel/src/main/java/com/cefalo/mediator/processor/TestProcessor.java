package com.cefalo.mediator.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * @author Humayun
 */
public class TestProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("In TestProcessor - " + exchange.getIn().getBody(String.class));
    }
}
