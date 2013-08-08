package be.ewils.decentralize.account;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class RegisterAccountTest extends CamelTestSupport {

    @Produce(uri = "direct:listAccounts")
    protected ProducerTemplate producerTemplate;

    @EndpointInject(uri = "mock:addAccounts")
    protected MockEndpoint resultEndpoint;

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                from("direct:listAccounts").to("mock:addAccounts");
            }
        };
    }

    @Test
    public void testFromListAccountsToFindAccounts() throws Exception {
        String expectedBody = "<accounts><id>123</id></accounts>";

        resultEndpoint.expectedBodiesReceived(expectedBody);

        producerTemplate.sendBody(expectedBody);

        resultEndpoint.assertIsSatisfied();
    }
}
