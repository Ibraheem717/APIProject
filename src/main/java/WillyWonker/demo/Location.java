package WillyWonker.demo;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Location {
    private String name;
    private String region;
    private String country;
    private double lat;
    private double lon;
    private String tz_id;
    private int localtime_epoch;
    private String localtime;

    public HashMap<String, Object> toDict() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("Name", this.name);
        dict.put("Region", this.region);
        dict.put("Country", this.country);
        dict.put("Lat", this.lat);
        dict.put("Lon", this.lon);
        dict.put("tz_id", this.tz_id);
        dict.put("Localtime_epoch", this.localtime_epoch);
        dict.put("Localtime", this.localtime);
        return dict;
    }

    public String getName() {
        return this.name;
    }
}
