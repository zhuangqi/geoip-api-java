package cn.wdz.geolite2.service;

import java.io.File;
import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Subdivision;

@Service
public class GeoipService {

    
    @Value("${geolite2.databases.path}")
    private String GEO_LITE2_DATABASES_PATH;

    private static final String GEO_LITE2_CITY_DATA_FILENAME = "GeoLite2-City.mmdb";


    public static class IpAddressInfo {
        private String ip;
        private Double lat;
        private Double lng;
        private String nation;
        private String province;
        private String city;
        public String getIp() {
            return ip;
        }
        public void setIp(String ip) {
            this.ip = ip;
        }
        public Double getLat() {
            return lat;
        }
        public void setLat(Double lat) {
            this.lat = lat;
        }
        public Double getLng() {
            return lng;
        }
        public void setLng(Double lng) {
            this.lng = lng;
        }
        public String getNation() {
            return nation;
        }
        public void setNation(String nation) {
            this.nation = nation;
        }
        public String getProvince() {
            return province;
        }
        public void setProvince(String province) {
            this.province = province;
        }
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city = city;
        }
        
    }

    public IpAddressInfo search(String ip) {

        String GEO_LITE2_CITY_DATA_PATH = GEO_LITE2_DATABASES_PATH + "/" + GEO_LITE2_CITY_DATA_FILENAME;

        IpAddressInfo info = new IpAddressInfo();
        info.setIp(ip);

        File database = new File(GEO_LITE2_CITY_DATA_PATH);
        
        try (DatabaseReader reader = new DatabaseReader.Builder(database).build()) {
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse response = reader.city(ipAddress);

            Country country = response.getCountry();
            info.setNation(country.getNames().get("zh-CN"));

            Subdivision subdivision = response.getMostSpecificSubdivision();
            info.setProvince(subdivision.getNames().get("zh-CN"));

            City city = response.getCity();
            info.setCity(city.getNames().get("zh-CN"));

            Location location = response.getLocation();
            info.setLat(location.getLatitude());
            info.setLng(location.getLongitude());
        } catch (Exception e) {
            throw new RuntimeException("search geolite2Databases error! "  + e.getMessage());
        }

        return info;
    }


}
