
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;


public class FiltersTest {

    public static ObjectMapper mapper = new ObjectMapper();;

    @Before
    public void before() {
       
    }

    @org.junit.Test
    public void jsonParseTest() throws IOException {
        Filters filters = new Filters();
        Filter filter = new Filter();
        filters.getFilters().add(filter);
        
        System.out.println(filters.writeValueAsString(filters));
        final String json = 
        "{\n" +
"    \"filters\":\n" +
"    [\n" +
"        {\n" +
"            \"field\": \"Name\",\n" +
"            \"operator\": \"contains\",\n" +
"            \"value\": \"John\"\n" +
"        },\n" +
"        {\n" +
"            \"filters\": [\n" +
"                {\n" +
"                    \"field\": \"Age\",\n" +
"                    \"operator\": \"gt\",\n" +
"                    \"value\": 20\n" +
"                },\n" +
"                {\n" +
"                    \"field\": \"Age\",\n" +
"                    \"operator\": \"lt\",\n" +
"                    \"value\": 85\n" +
"                }\n" +
"            ],\n" +
"            \"logic\": \"and\"\n" +
"        },\n" +
"        {\n" +
"            \"field\": \"Address\",\n" +
"            \"operator\": \"doesnotcontain\",\n" +
"            \"value\": \"street\"\n" +
"        }\n" +
"    ],\n" +
"    \"logic\": \"or\"\n" +
"}";
        filters = mapper.readValue(json, Filters.class);
        Assert.assertEquals(json.replaceAll("\n", "").replaceAll(" ", ""), filters.writeValueAsString(filters).replaceAll("\n", "").replaceAll(" ", ""));
    }
}
