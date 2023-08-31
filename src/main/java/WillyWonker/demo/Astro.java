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
        dict.put("Moon Phase", this.moon_phase);
        dict.put("Moon Illumination", this.moon_illumination);
        return dict;
    }
}
