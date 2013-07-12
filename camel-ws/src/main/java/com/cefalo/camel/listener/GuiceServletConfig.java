package com.cefalo.camel.listener;

import com.cefalo.camel.mapping.MyCamelConfig;
import com.cefalo.camel.mapping.CamelGuiceConfig;
import com.cefalo.camel.route.AnotherRoute;
import com.cefalo.camel.route.MyRouteBuilder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.apache.camel.CamelContext;
import org.apache.camel.guice.CamelModuleWithRouteTypes;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author humayun
 */
public class GuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        Injector injector = Guice.createInjector(new CamelGuiceConfig(), new MyCamelConfig());

        try {
            CamelContext camelContext = injector.getInstance(CamelContext.class);
            camelContext.addRoutes(new MyRouteBuilder());
            System.err.println("Here is an error - " + camelContext.toString());
            camelContext.start();
            camelContext.addRoutes(new AnotherRoute());
        } catch (Exception e) {
            System.out.println(e);
        }

        return injector;
    }
}
