package WillyWonker.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DemoApplicationTest {

    @Test
    @DisplayName("Test questionRun")
    void testRun() throws IOException {
        DemoApplication demo = new DemoApplication();
        List<Referential> list = demo.run("mathematics");
        assertAll("Should return questions if a catagory exist",
                () -> assertNotNull(demo.run("mathematics").get(0) ),
                () -> assertNotNull(demo.run("").get(0) ),
                () -> assertThrows(IndexOutOfBoundsException.class , () ->demo.run("null").get(0) )
        );

    }

}