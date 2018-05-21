package Model;

public class Address {
    String country;
    String city;
    String region;
    String street;
    String house;
    String housing;
    String flat;

    public Address(String sCountry, String sCity, String sRegion, String sStreet, String sHouse, String sHousing, String sFlat){
        this.country = sCountry;
        this.city = sCity;
        this.region = sRegion;
        this.street = sStreet;
        this.house = sHouse;
        this.housing = sHousing;
        this.flat = sFlat;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getHousing() {
        return housing;
    }

    public String getFlat() {
        return flat;
    }
}
