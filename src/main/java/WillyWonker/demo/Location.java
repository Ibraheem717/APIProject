package WillyWonker.demo;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Location {
    public String name;
    public String region;
    public String country;
    public double lat;
    public double lon;
    public String tz_id;
    public int localtime_epoch;
    public String localtime;

    @Override
    public String toString() {
        return  "Name: " + name + '\n' +
                "Region: " + region + '\n' +
                "Country: " + country + '\n' +
                "Lat: " + lat +'\n' +
                "Lon: " + lon +'\n' +
                "tz_id: " + tz_id + '\n' +
                "Localtime_epoch: " + localtime_epoch +'\n' +
                "Localtime: " + localtime + '\n';
    }
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
