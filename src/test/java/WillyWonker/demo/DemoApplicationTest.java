package WillyWonker.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DemoApplicationTest {

    @Test
    @DisplayName("Test Question API")
    void testRun() throws IOException {
        TriviaAPI trivia = new TriviaAPI(new String[]{"API", "questionAPI", "astrologyAPI"});
        assertAll("Should return questions if a catagory exist: " +
                        "a mathematic question, a random question and no question",
                () -> assertFalse( trivia.testQuestiontionAPI("mathematics").isEmpty() ),
                () -> assertFalse( trivia.testQuestiontionAPI("").isEmpty() ),
                () -> assertTrue( trivia.testQuestiontionAPI("null").isEmpty() )
        );
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