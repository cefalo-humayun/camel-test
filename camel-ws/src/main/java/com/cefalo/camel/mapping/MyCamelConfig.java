package com.cefalo.camel.mapping;

import com.cefalo.camel.route.MyRouteBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.apache.camel.CamelContext;
import org.apache.camel.guice.CamelModule;
import org.apache.camel.guice.CamelModuleWithMatchingRoutes;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author Humayun
 */
public class MyCamelConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(CamelContext.class).to(DefaultCamelContext.class).in(Scopes.SINGLETON);
    }
}
