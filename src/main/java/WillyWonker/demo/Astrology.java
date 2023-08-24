package WillyWonker.demo;

public class Astrology {
    public Location location;
    public Astronomy astronomy;

    @Override
    public String toString() {
        return "Astrology: \n" +
                "Location: \n" + location +
                "Astronomy: \n" + astronomy ;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }
}

