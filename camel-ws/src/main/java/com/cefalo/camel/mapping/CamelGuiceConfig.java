package com.cefalo.camel.mapping;

import com.cefalo.camel.resource.CamelResource;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author humayun
 */
public class CamelGuiceConfig extends JerseyServletModule {
    @Override
    protected void configureServlets() {
        bind(CamelResource.class);

        Map<String, String> params = new HashMap<>();
        params.put(ServletContainer.FEATURE_FILTER_FORWARD_ON_404, "true");
        params.put(ServletContainer.JSP_TEMPLATES_BASE_PATH, "/WEB-INF/jsp");
        params.put(ServletContainer.PROPERTY_WEB_PAGE_CONTENT_REGEX, "/static/(.)*");

        filter("/*").through(GuiceContainer.class, params);
    }
}
