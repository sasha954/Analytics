package ua.nure.fomenko.analytics.db.entity;

/**
 * Created by fomenko on 25.02.2017.
 */
public class Visiters extends Entity {
    private String ip;
    private String country_code;
    private String country;
    private String country_rus;
    private String region;
    private String region_rus;
    private String city;
    private String city_rus;
    private String latitude;
    private String longitude;
    private int zip_code;
    private String time_zone;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_rus() {
        return country_rus;
    }

    public void setCountry_rus(String country_rus) {
        this.country_rus = country_rus;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion_rus() {
        return region_rus;
    }

    public void setRegion_rus(String region_rus) {
        this.region_rus = region_rus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity_rus() {
        return city_rus;
    }

    public void setCity_rus(String city_rus) {
        this.city_rus = city_rus;
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

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Visiter=[")
                .append("ip: ").append(ip).append(",\n")
                .append("country code: ").append(country_code).append(",\n")
                .append("country: ").append(country).append(",\n")
                .append("country rus: ").append(country_rus).append(",\n")
                .append("region: ").append(region).append(",\n")
                .append("region rus: ").append(region_rus).append(",\n")
                .append("city: ").append(city).append(",\n")
                .append("city rus: ").append(city_rus).append(",\n")
                .append("latitude: ").append(latitude).append(",\n")
                .append("longitude: ").append(longitude).append(",\n")
                .append("zip code: ").append(zip_code).append(",\n")
                .append("time zone: ").append(time_zone).append("]");
        return builder.toString();
    }
}
