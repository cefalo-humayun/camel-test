package http;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author humayun
 */
public class MyTimerRoute extends RouteBuilder {
    MyStopWatch stopWatch = new MyStopWatch();

    @Override
    public void configure() throws Exception {
        from("direct:ab")
                .bean(stopWatch, "start")
                .log("Here body is ${body}")
                .setBody(simple("The body is changed"))
                .log("The modified body - ${body}")
                .bean(stopWatch, "sleep")
                .bean(stopWatch, "stop")
                .bean(stopWatch, "getCountTime")
                .log("we are here for another timer")
                .bean(stopWatch, "start")
                .log("we are middle of timer")
                .bean(stopWatch, "stop")
                .bean(stopWatch, "getCountTime");


    }
}
