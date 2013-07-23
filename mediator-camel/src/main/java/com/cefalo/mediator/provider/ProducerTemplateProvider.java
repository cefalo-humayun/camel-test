package com.cefalo.mediator.provider;

import com.google.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import javax.inject.Provider;

/**
 * @author Humayun
 */
public class ProducerTemplateProvider implements Provider<ProducerTemplate> {
    private final ProducerTemplate producerTemplate;

    @Inject
    public ProducerTemplateProvider(CamelContext camelContext) {
        producerTemplate = camelContext.createProducerTemplate();
    }

    @Override
    public ProducerTemplate get() {
        return producerTemplate;
    }
}
