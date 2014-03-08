
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;

public class Filters {
    
    private List<Filter> filters = new ArrayList<Filter>();
    private String logic;
    // accessors/mutators/toString

    String writeValueAsString(Filters filters) throws JsonProcessingException {
        return FiltersTest.mapper.writeValueAsString(filters);
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
    }
}