package aggregation;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * @author humayun
 */
public class Aggregate1 implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange != null) {
            String a = oldExchange.getIn().getBody(String.class);
            String b = newExchange.getIn().getBody(String.class);
            newExchange.getIn().setBody( a + b );
            return newExchange;
        }
        System.err.println("ex 1 " + oldExchange);
        System.err.println("ex 2 " + newExchange);
        return newExchange;
    }
}
