package WillyWonker.demo;

import java.util.HashMap;

public class Astronomy {
    private Astro astro;

    public HashMap<String, Object> toDict() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("Astro", this.astro.toDict());
        return dict;
    }
}
