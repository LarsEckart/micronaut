package micro.teststub;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Conference {

    private final String name;
    private final String location;

    @JsonCreator
    public Conference(@JsonProperty("name") String name, @JsonProperty("location") String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
