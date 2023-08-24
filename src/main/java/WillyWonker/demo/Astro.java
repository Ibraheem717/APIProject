package WillyWonker.demo;

public class Astro {
    public String sunrise;
    public String sunset;
    public String moonrise;
    public String moonset;
    public String moon_phase;
    public String moon_illumination;
    public int is_moon_up;
    public int is_sun_up;

    @Override
    public String toString() {
        return  "Sunrise: " + sunrise + '\n' +
                "Munset: " + sunset + '\n' +
                "Moonrise: " + moonrise + '\n' +
                "Moonset: " + moonset + '\n' +
                "Moon_phase: " + moon_phase + '\n' +
                "Moon_illumination: " + moon_illumination + '\n' +
                "is_moon_up: " + is_moon_up + '\n' +
                "is_sun_up: " + is_sun_up + '\n';
    }
}
