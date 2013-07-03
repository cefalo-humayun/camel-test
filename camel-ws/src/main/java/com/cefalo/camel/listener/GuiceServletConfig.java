package com.cefalo.camel.listener;

import com.cefalo.camel.mapping.CamelGuiceConfig;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * @author humayun
 */
public class GuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        Injector injector = Guice.createInjector(new CamelGuiceConfig());

        return injector;
    }
}
