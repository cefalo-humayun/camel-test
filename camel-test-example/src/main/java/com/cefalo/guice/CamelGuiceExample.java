package com.cefalo.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.guice.CamelModuleWithRouteTypes;

/**
 * @author Humayun
 */
public class CamelGuiceExample {

    public static void main(String args[]) throws Exception {
        /*Injector injector = Guice.createInjector(new CamelModuleWithRouteTypes(MyRouteBuilder.class));
        CamelContext camelContext = injector.getInstance(CamelContext.class);

        ProducerTemplate template = camelContext.createProducerTemplate();
        camelContext.start();
        template.sendBody("direct:ab", "Humayun");*/
    }
}
