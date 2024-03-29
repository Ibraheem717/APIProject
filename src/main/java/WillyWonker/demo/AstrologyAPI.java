package WillyWonker.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubspot.jinjava.Jinjava;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AstrologyAPI {
    final ObjectMapper objectMapper = new ObjectMapper();
    String [] listOfAPI;
    List<Astrology> astrologyArr;
    Set<String> location;
    String keyName="RAPID_API_KEY";
    String keyValue=System.getenv(keyName);
    public AstrologyAPI(String [] arr) {
        this.listOfAPI=arr;
        this.astrologyArr = new ArrayList<>();
        this.location = new HashSet<>();
    }
    public Set<String> getLocation() {
        return location;
    }

    public List<Astrology> getAstrologyArr() {
        return astrologyArr;
    }

    public void getAstrologyAPI() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Astrology astroLocation;
        this.astrologyArr.clear();
        String [] listOfLocations = this.location.toArray(new String[0]);
        for (String name : listOfLocations) {
            Request request = new Request.Builder()
                    .url("https://weatherapi-com.p.rapidapi.com/astronomy.json?q=" + name)
                    .get()
                    .addHeader("X-RapidAPI-Key", DemoApplication.keyValue)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                        astroLocation = objectMapper.readValue(responseBody, Astrology.class);
                    this.astrologyArr.add(astroLocation);
                    this.location.remove(name);
                    this.location.add(astroLocation.getName());
                }
            } catch (IOException e) {
                assert true;
            }
        }
    }
    public String renderTemplate() {
        Jinjava jinjava = new Jinjava();
        Map<String, Object> context = new HashMap<>();
        Map<String, Object> astrologyContext = new HashMap<>();
        if (!astrologyArr.isEmpty()) {
            for (int i = 0; i < astrologyArr.size(); i++)
                astrologyContext.put(astrologyArr.get(i).getName(), astrologyArr.get(i).toDict());
        }
        context.put("astrology", astrologyContext);
        context.put("menu", this.listOfAPI);
        String temp = null;
        try {
            temp = Files.readString(Paths.get("src/main/resources/astrology.html"));
        }
        catch (IOException e) {
            assert true;
        }
        return jinjava.render(temp, context);
    }
    public String removeLocation(String location) {
        if (this.astrologyArr!=null)
            this.location.remove(location);
        this.getAstrologyAPI();
        return this.renderTemplate();
    }
    public String addLocation(String location) {
        if (this.astrologyArr!=null) {
            this.location.add(location);
        }
        this.getAstrologyAPI();
        return this.renderTemplate();
    }
    public List<Astrology> testAstrologyAPI(HashSet<String> set) {
        this.location=set;
        this.getAstrologyAPI();
        return this.astrologyArr;
    }
}
