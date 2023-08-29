package WillyWonker.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AstrologyAPITest {



    @Test
    @DisplayName("Test Astrology remove & add location")
    void addAndremoveLocation() throws IOException, InterruptedException {
        AstrologyAPI astrology = new AstrologyAPI(new String[]{"API", "questionAPI", "astrologyAPI"});
        astrology.addLocation("London");
        assertFalse(astrology.getLocation().isEmpty());
        astrology.removeLocation("London");
        assertTrue(astrology.getLocation().isEmpty());
    }

    @Test
    @DisplayName("Test Astrology API")
    void testAstrologyRun() throws IOException {
        AstrologyAPI astrology = new AstrologyAPI(new String[]{"API", "questionAPI", "astrologyAPI"});
        assertAll("Should return questions if a catagory exist : " +
                        "Just London, London and Berlin and no location",
                () -> assertFalse(astrology.testAstrologyAPI(
                        new HashSet<>(List.of("London"))).isEmpty() ),
                () -> assertFalse(astrology.testAstrologyAPI(
                        new HashSet<>(List.of("London", "Berlin"))).isEmpty() ),
                () -> assertTrue(astrology.testAstrologyAPI(
                        new HashSet<>(List.of(""))).isEmpty() )
        );
    }
}