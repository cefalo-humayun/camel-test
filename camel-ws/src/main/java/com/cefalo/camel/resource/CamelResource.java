package com.cefalo.camel.resource;

import com.cefalo.camel.processor.CommandProcessor;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.http.HttpStatus;

import javax.jms.ConnectionFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author humayun
 */
@Path("notifications")
public class CamelResource {

    @EndpointInject(uri="direct:ab")
    ProducerTemplate producer;


    @POST
    @Path("/np")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public Response processNPNotification(@DefaultValue("") String commandText) throws Exception {

        System.out.println("we are here-\n" + commandText);
        boolean commandProcessed = true;

        /*CamelContext camelContext = new DefaultCamelContext();

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        camelContext.addRoutes(new RouteBuilder() {
            public void configure() {
                from("jms:queue:start").to("jms:queue:command");
            }
        });

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("jms:queue:command")
                        .process(new CommandProcessor())

                        *//*.log(body(String.class) + "hereHERE")*//*

                        .pipeline(simple("${body}").toString(), "jms:queue:assignment");
            }
        });

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("jms:queue:assignment")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                System.err.println(exchange.getIn().getBody(String.class));
                            }
                        })
                        .to("file:/home/sky/Desktop/camel/data/");
            }
        });

        ProducerTemplate template = camelContext.createProducerTemplate();
        camelContext.start();
        template.sendBody("jms:queue:start", commandText);

        camelContext.start();
        Thread.sleep(10000);
        camelContext.stop();*/

        producer.sendBody(commandText);
        return Response.status(commandProcessed ? HttpStatus.SC_OK : HttpStatus.SC_ACCEPTED).build();
    }

    @GET
    @Path("/test")
    public Response testMethod() {
        System.out.println("Yes we are here.");
        return Response.status(HttpStatus.SC_OK).build();
    }
}
