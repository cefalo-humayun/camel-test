package com.cefalo.mediator.converter;

import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.apache.camel.converter.stream.InputStreamCache;

/**
 * @author Humayun
 */
@Converter
public class TestConverter {

    @Converter
    public String getTestConverter(InputStreamCache data, Exchange exchange) throws Exception {
        return "";
    }
}
