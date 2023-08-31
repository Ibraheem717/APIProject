package WillyWonker.demo;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Astrology {
    private Location location;
    private Astronomy astronomy;

    public HashMap<String, Object> toDict() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("Location", this.location.toDict());
        dict.put("Astronomy", this.astronomy.toDict());
        return dict;
    }
    public String getName() {
        return location.getName();
    }
}

