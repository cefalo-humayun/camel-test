package com.shadesh;

import beans.MyService;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

/**
 * @author Humayun
 */
public class ConsumeTest {

    public static void main(String args[]) throws Exception {
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("serviceBean", new MyService("direct:ab"));

        CamelContext camelContext = new DefaultCamelContext(registry);

        ProducerTemplate template = camelContext.createProducerTemplate();
        camelContext.start();
        template.sendBody("direct:ab", "Humayun");

        Thread.sleep(5000);
        camelContext.stop();
    }
}
