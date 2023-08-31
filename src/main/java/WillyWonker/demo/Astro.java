package WillyWonker.demo;

import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Astro {
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private String moon_phase;
    private String moon_illumination;
    private int is_moon_up;
    private int is_sun_up;

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
