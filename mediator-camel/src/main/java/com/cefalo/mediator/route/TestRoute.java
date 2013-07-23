package com.cefalo.mediator.route;

import com.cefalo.mediator.processor.TestProcessor;
import org.apache.camel.builder.RouteBuilder;

/**
 * @author Humayun
 */
public class TestRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:ab")
                .process(new TestProcessor());

    }
}
