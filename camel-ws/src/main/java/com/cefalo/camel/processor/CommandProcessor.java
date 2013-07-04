package com.cefalo.camel.processor;

import nu.xom.Builder;
import nu.xom.Document;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.StringReader;

/**
 * @author Humayun
 */
public class CommandProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String commandXML = exchange.getIn().getBody(String.class);
        Builder parse = new Builder();
        Document doc = parse.build(new StringReader(commandXML));
        System.err.println(doc.query("//link[@rel='assignment']/@href").get(0).getValue());
        String assignmentURL = doc.query("//link[@rel='assignment']/@href").get(0).getValue();
        exchange.getIn().setBody(assignmentURL);
    }
}
