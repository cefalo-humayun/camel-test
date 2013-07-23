package aggregation;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * @author humayun
 */
public class AggregationStrategy1 implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        System.err.println("in 1, oldExchange - \n" + oldExchange.getIn().getBody(String.class));
        System.err.println("in 1, newExchange - \n" + newExchange.getIn().getBody(String.class));
        return newExchange;
    }
}
