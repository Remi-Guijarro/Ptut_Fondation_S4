package fr.univ.amu.Object_Structure;

public class Coordonée {
    private double lat;
    private double longitude;

    public Coordonée(double lat, double longitude) {
        this.lat = lat;
        this.longitude = longitude;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void display() {
        System.out.println("lat: '" + this.lat + "' | longitude : '" + this.longitude + "'");
    }
}
