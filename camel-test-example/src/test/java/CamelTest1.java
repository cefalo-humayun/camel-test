import beans.HelloBean;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.junit.Test;

/**
 * @author humayun
 */
public class CamelTest1 extends TestCase{
    private CamelContext context;
    private ProducerTemplate template;

    protected void setUp() throws Exception {
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("helloBean", new HelloBean());

        context = new DefaultCamelContext(registry);
        template = context.createProducerTemplate();
        context.addRoutes(new RouteBuilder() {
            public void configure() throws Exception {
                from("direct:hello").beanRef("helloBean");
            }
        });
        context.start();
    }

    protected void tearDown() throws Exception {
        template.stop();
        context.stop();
    }

    @Test
    public void testHello() throws Exception {
        Object reply = template.requestBody("direct:hello", "World");
        Assert.assertEquals("Hello World", reply);
    }
}
