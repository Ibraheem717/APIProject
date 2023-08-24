package WillyWonker.demo;

public class Location {
    public String name;
    public String region;
    public String country;
    public double lat;
    public double lon;
    public String tz_id;
    public int localtime_epoch;
    public String localtime;

    @Override
    public String toString() {
        return  "Name: " + name + '\n' +
                "Region: " + region + '\n' +
                "Country: " + country + '\n' +
                "Lat: " + lat +'\n' +
                "Lon: " + lon +'\n' +
                "tz_id: " + tz_id + '\n' +
                "Localtime_epoch: " + localtime_epoch +'\n' +
                "Localtime: " + localtime + '\n';
    }
}
