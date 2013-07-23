package http;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.converter.DefaultTypeConverter;
import org.apache.camel.spi.TypeConverterRegistry;

import java.io.InputStream;

/**
 * @author humayun
 */
public class HTTPTest1 {
    public static void main(String ...args) throws Exception {

        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:ab")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                System.err.println("in process - " + exchange.getIn().getBody(String.class));
                            }
                        })
                        .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                        .to("http://newspilot.cefalo.com.bd/webservice/articles/12?authMethod=Basic&authUsername=cefalo&authPassword=cefalo")
                        .convertBodyTo(Document.class)
                        .to("direct:cd");

                from("direct:cd")
                        .setHeader(Exchange.HTTP_METHOD, constant("PUT"))
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                System.err.println("getIn() - " + exchange.getIn().getBody());
                                System.err.println(":-) nice");
                                //System.err.println("getOut() - " + exchange.getOut().getBody(String.class));
                                Document doc = exchange.getIn().getBody(Document.class);
                                System.out.println("hi - " + doc.toXML());

                                Element newElement = new Element("name", doc.getRootElement().getNamespaceURI());
                                newElement.appendChild("This is modified nameeeee another modified name");

                                Element oldElement = doc.getRootElement().getFirstChildElement("name", doc.getRootElement().getNamespaceURI());
                                System.err.println("old value - " + oldElement.getValue());

                                doc.getRootElement().replaceChild(oldElement, newElement);

                                System.out.println(doc);
                                exchange.getIn().setBody(doc);
                                exchange.getIn().setHeader("If-Match", exchange.getIn().getHeader("ETag"));
                                System.out.println("Etag is " + exchange.getIn().getHeaders());

                            }
                        })
                        .convertBodyTo(InputStream.class)
                        .to("http://newspilot.cefalo.com.bd/webservice/articles/12?authMethod=Basic&authUsername=cefalo&authPassword=cefalo")
                        //.to(xpath("//atom:link[@rel='self']/@href").toString())
                        .convertBodyTo(Document.class)
                        .log("Here body - ${body}");
            }
        });

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        camelContext.start();
        String commandText = "<command>\n" +
                "<type>Export</type>\n" +
                "<link href=\"http://newspilot.cefalo.com.bd/webservice/assignments/3203\" rel=\"assignment\" title=\"Export amitestkorchi-sky-54\"/>\n" +
                "<link href=\"http://newspilot.cefalo.com.bd/webservice/products/9\" rel=\"product\" title=\"Humayun\"/>\n" +
                "</command>";
        producerTemplate.sendBody("direct:ab", commandText);

        Thread.sleep(3000);
        camelContext.stop();
    }
}
