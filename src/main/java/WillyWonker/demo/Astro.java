package WillyWonker.demo;

import java.util.HashMap;

class Astro {
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private String moonPhase;
    private String moonIllumination;
    private int isMoonUp;
    private int isSunUp;

    public HashMap<String, Object> toDict() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("Sunrise", this.sunrise);
        dict.put("Sunset", this.sunset);
        dict.put("Moonrise", this.moonrise);
        dict.put("Moonset", this.moonset);
        dict.put("Moon_phase", this.moonPhase);
        dict.put("Moon_illumination", this.moonIllumination);
        dict.put("is_moon_up", this.isMoonUp);
        dict.put("is_sun_up", this.isSunUp);
        return dict;
    }
}
