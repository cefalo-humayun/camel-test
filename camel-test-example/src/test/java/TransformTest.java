/**
 * @author humayun
 */
import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;

/**
 * @version
 */
public class TransformTest extends ContextTestSupport {
    protected MockEndpoint resultEndpoint;

    public void testSendingAMessageUsingMulticastReceivesItsOwnExchange() throws Exception {
        resultEndpoint.expectedBodiesReceived("Hello World!");

        sendBody("direct:start", "Hello");

        resultEndpoint.assertIsSatisfied();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        resultEndpoint = getMockEndpoint("mock:result");
    }

    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                // START SNIPPET: example
                from("direct:start").process(new Processor() {
                    public void process(Exchange exchange) {
                        Message in = exchange.getIn();
                        in.setBody(in.getBody(String.class) + " World!");
                    }
                }).to("mock:result");
                // END SNIPPET: example
            }
        };
    }
}