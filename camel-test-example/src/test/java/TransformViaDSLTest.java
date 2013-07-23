/**
 * @author humayun
 */
import org.apache.camel.ContextTestSupport;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;

/**
 * @version
 */
public class TransformViaDSLTest extends ContextTestSupport {
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
                from("direct:start").setBody(body().append(" World!")).to("mock:result");
                // END SNIPPET: example
            }
        };
    }
}