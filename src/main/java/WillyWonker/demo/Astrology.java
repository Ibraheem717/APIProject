package WillyWonker.demo;

import java.util.HashMap;
import java.util.Map;


public class Astrology {
    public Location location;
    public Astronomy astronomy;

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

