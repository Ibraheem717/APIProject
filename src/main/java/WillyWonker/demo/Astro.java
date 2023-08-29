package WillyWonker.demo;

import java.util.HashMap;

public class Astro {
    public String sunrise;
    public String sunset;
    public String moonrise;
    public String moonset;
    public String moon_phase;
    public String moon_illumination;
    public int is_moon_up;
    public int is_sun_up;

    public HashMap<String, Object> toDict() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("Sunrise", this.sunrise);
        dict.put("Sunset", this.sunset);
        dict.put("Moonrise", this.moonrise);
        dict.put("Moonset", this.moonset);
        dict.put("Moon_phase", this.moon_phase);
        dict.put("Moon_illumination", this.moon_illumination);
        dict.put("is_moon_up", this.is_moon_up);
        dict.put("is_sun_up", this.is_sun_up);
        return dict;
    }
}
