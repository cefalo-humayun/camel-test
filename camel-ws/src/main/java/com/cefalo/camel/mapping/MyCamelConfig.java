package com.cefalo.camel.mapping;

import com.google.inject.AbstractModule;
import org.apache.camel.CamelContext;
import org.apache.camel.guice.CamelModule;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author Humayun
 */
public class MyCamelConfig extends CamelModule {

    @Override
    protected void configure() {
        super.configure();
    }
}
