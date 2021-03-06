/**
 * @author humayun
 */
import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.processor.BatchProcessor;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * @version $Revision$
 */
public class AggregatorTest extends ContextTestSupport {
    protected int messageCount = 100;

    public void testSendingLotsOfMessagesGetAggregatedToTheLatestMessage() throws Exception {
        MockEndpoint resultEndpoint = resolveMandatoryEndpoint("mock:result", MockEndpoint.class);

        resultEndpoint.expectedBodiesReceived("message:" + messageCount);

        // lets send a large batch of messages
        for (int i = 1; i <= messageCount; i++) {
            String body = "message:" + i;
            template.sendBodyAndHeader("direct:start", body, "cheese", 123);
        }

        resultEndpoint.assertIsSatisfied();
    }

    public void testPredicate() throws Exception {
        testSendALargeBatch("direct:predicate");
    }

    public void testOutBatchPredicate() throws Exception {
        testSendALargeBatch("direct:outBatchPredicate");
    }

    public void testOutBatchWithNoInBatching() throws Exception {
        testSendALargeBatch("direct:outBatchNoInBatching");
    }

    public void testOneMessage() throws Exception {
        MockEndpoint resultEndpoint = resolveMandatoryEndpoint("mock:result", MockEndpoint.class);

        resultEndpoint.expectedMessageCount(1);
        template.sendBodyAndHeader("direct:predicate", "test", "aggregated", 5);
        resultEndpoint.assertIsSatisfied();
    }

    public void testBatchTimeoutExpiry() throws Exception {
        MockEndpoint resultEndpoint = resolveMandatoryEndpoint("mock:result", MockEndpoint.class);
        resultEndpoint.expectedMessageCount(1);
        resultEndpoint.setSleepForEmptyTest(2 * BatchProcessor.DEFAULT_BATCH_TIMEOUT);
        template.sendBodyAndHeader("direct:start", "message:1", "cheese", 123);
        resultEndpoint.assertIsSatisfied();
    }


    public void testAggregatorNotAtStart() throws Exception {
        MockEndpoint resultEndpoint = resolveMandatoryEndpoint("mock:result", MockEndpoint.class);
        resultEndpoint.expectedMessageCount(1);
        resultEndpoint.message(0).header("visited").isNotNull();
        resultEndpoint.setSleepForEmptyTest(2 * BatchProcessor.DEFAULT_BATCH_TIMEOUT);
        template.sendBodyAndHeader("seda:header", "message:1", "cheese", 123);
        resultEndpoint.assertIsSatisfied();
    }

    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {

                // START SNIPPET: ex
                // in this route we aggregate all from direct:state based on the header id cheese
                from("direct:start")
                        .aggregate(header("cheese"))
                        .to("mock:result");

                from("seda:header")
                        .setHeader("visited", constant(true))
                        .aggregate(header("cheese"))
                        .to("mock:result");

                // in this sample we aggregate using our own strategy with a completion predicate
                // stating that the aggregated header is equal to 5.
                from("direct:predicate")
                        .aggregate(header("cheese"), new MyAggregationStrategy())
                        .completionPredicate(header("aggregated").isEqualTo(5))
                        .to("mock:result");

                // this sample is similar to the one above but it also illustrates the use of outBatchSize
                // to send exchanges to mock:endpoint in batches of 10.
                from("direct:outBatchPredicate")
                        .aggregate(header("cheese"), new MyAggregationStrategy())
                        .completionPredicate(header("aggregated").isEqualTo(5))/*.outBatchSize(10)*/
                        .to("mock:result");
                // END SNIPPET: ex

                // turning off in batching (batchSize = 1) is a good way to test "out" batching.  Don't include
                // in wiki snippet as it may not be a good example to follow.
                from("direct:outBatchNoInBatching")
                        .aggregate(header("cheese"), new MyAggregationStrategy())
                        .completionPredicate(header("aggregated").isEqualTo(5))
                        /*.batchSize(1).outBatchSize(10)*/
                        .to("mock:result");
            }
        };
    }

    private void testSendALargeBatch(String endpointUri) throws Exception {
        MockEndpoint resultEndpoint = resolveMandatoryEndpoint("mock:result", MockEndpoint.class);

        resultEndpoint.expectedMessageCount(messageCount / 5);
        // lets send a large batch of messages
        for (int i = 1; i <= messageCount; i++) {
            String body = "message:" + i;
            template.sendBodyAndHeader(endpointUri, body, "cheese", 123);
            // need a little sleep when sending large batches
            Thread.sleep(2);
        }

        resultEndpoint.assertIsSatisfied();

    }

    private static class MyAggregationStrategy implements AggregationStrategy {

        public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
            if (oldExchange == null) {
                // the first time we only have the new exchange so it wins the first round
                return newExchange;
            }
            int oldPrice = oldExchange.getIn().getBody(Integer.class);
            int newPrice = newExchange.getIn().getBody(Integer.class);
            // return the "winner" that has the highest price
            return newPrice > oldPrice ? newExchange : oldExchange;
        }
    }
}