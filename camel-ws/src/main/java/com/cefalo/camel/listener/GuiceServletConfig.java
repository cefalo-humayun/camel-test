package com.cefalo.camel.listener;

import com.cefalo.camel.mapping.MyCamelConfig;
import com.cefalo.camel.mapping.CamelGuiceConfig;
import com.cefalo.camel.route.MyRouteBuilder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author humayun
 */
public class GuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        Injector injector = Guice.createInjector(new CamelGuiceConfig());


        try {
            CamelContext camelContext = new DefaultCamelContext();
            camelContext.addRoutes(new MyRouteBuilder());
            camelContext.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return injector;
    }
}
