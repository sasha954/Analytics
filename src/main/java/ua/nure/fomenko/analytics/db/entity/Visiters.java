package ua.nure.fomenko.analytics.db.entity;

/**
 * Created by fomenko on 25.02.2017.
 */
public class Visiters extends Entity {
    private String ip;
    private String countryCode;
    private String country;
    private String countryRus;
    private String region;
    private String regionRus;
    private String city;
    private String cityRus;
    private String latitude;
    private String longitude;
    private int zipCode;
    private String timeZone;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryRus() {
        return countryRus;
    }

    public void setCountryRus(String countryRus) {
        this.countryRus = countryRus;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionRus() {
        return regionRus;
    }

    public void setRegionRus(String regionRus) {
        this.regionRus = regionRus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityRus() {
        return cityRus;
    }

    public void setCityRus(String cityRus) {
        this.cityRus = cityRus;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
