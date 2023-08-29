package WillyWonker.demo;
import java.util.HashMap;
public class Location {
    public String name;
    public String region;
    public String country;
    public double lat;
    public double lon;
    public String tz_id;
    public int localtime_epoch;
    public String localtime;

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
