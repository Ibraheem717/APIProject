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
    void testQuestionRun() throws IOException, InterruptedException {
        DemoApplication demo = new DemoApplication();
        demo.questionAPI();
        assertFalse(demo.getTrivia().getLastQuestion().isEmpty());
    }

    @Test
    @DisplayName("Test Astrology API")
    void testAstrologyRun() throws IOException, InterruptedException {
        DemoApplication demo = new DemoApplication();
        demo.astrologyAPI();
        assertTrue(demo.getAstrology().getAstrologyArr().isEmpty());
    }

    @Test
    @DisplayName("Test Home API")
    void testHomeRun() throws IOException, InterruptedException {
        DemoApplication demo = new DemoApplication();
        String temp = demo.homePage();
        assertEquals("<!DOCTYPE html", temp.split(">")[0]);
    }

}