package be.ewils.decentralize.account;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class CamelTest extends CamelTestSupport {

    @EndpointInject(uri = "mock:findAccounts")
    protected MockEndpoint resultEndpoint;
    @Produce(uri = "direct:listAccounts")
    protected ProducerTemplate template;

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                from("direct:listAccounts").to("mock:findAccounts");
            }
        };
    }

    @Test
    public void testFromListAccountsToFindAccounts() throws Exception {
        String expectedBody = "<listAccounts><id>123</id></listAccounts>";

        resultEndpoint.expectedBodiesReceived(expectedBody);

        template.sendBody(expectedBody);

        resultEndpoint.assertIsSatisfied();
    }
}
