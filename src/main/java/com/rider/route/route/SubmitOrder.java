package com.rider.route.route;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Antoine
 * Date: 8/6/13
 * Time: 10:39 PM
 */
public class SubmitOrder {

    /**
     * constructor
     * @throws Exception when Camel does
     */
    public SubmitOrder() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

        CamelContext context = new DefaultCamelContext();
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        RouteBuilder routeBuilder = new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("ftp://rider.com/orders?username=rider&password=secret").
                to("jms:queue:incomingOrders");
            }
        };

        context.addRoutes(routeBuilder);
    }
}
