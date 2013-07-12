package com.cefalo.guice;

import com.google.inject.AbstractModule;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.guice.CamelModuleWithMatchingRoutes;
import org.apache.camel.guice.CamelModuleWithRouteTypes;
import org.apache.camel.guice.GuiceCamelContext;

/**
 * @author Humayun
 */
public class MyModule extends CamelModuleWithMatchingRoutes {

    @Override
    protected void configure() {
        super.configure();
        bind(MyRouteBuilder.class);
    }

}
