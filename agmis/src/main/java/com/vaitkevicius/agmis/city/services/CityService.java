package com.vaitkevicius.agmis.city.services;

import com.vaitkevicius.agmis.city.data.db.City;
import com.vaitkevicius.agmis.city.data.repositories.CityRepository;
import com.vaitkevicius.agmis.weatherMap.services.WeatherMapService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * *
 * Created By Povilas 08/02/2019
 * *
 **/
@Service
@Log4j2
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherMapService weatherMapService;

    public City saveCity(City city) {
        log.info("Creating new city with name {}", city.getCityName());
        City newCity = City.builder()
                .id(getNewCityId(city.getCityName()))
                .cityName(city.getCityName())
                .minTemperature(city.getMinTemperature())
                .maxTemperature(city.getMaxTemperature())
                .weatherCondition(city.getWeatherCondition())
                .area(city.getArea())
                .population(city.getPopulation())
                .pressure(city.getPressure())
                .humidity(city.getHumidity())
                .build();
        return cityRepository.save(newCity);
    }

    private String getNewCityId(String cityName){
        return weatherMapService.getCityId(cityName);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public List<City> getCitiesByFilterParams(String area, String population) {
        return cityRepository.findAllByAreaOrPopulation(area, population);
    }

    public City getCityByName(String cityName, boolean isShowing) {
        System.out.println("cityName " + cityName);
        System.out.println("test " + isShowing);
        return cityRepository.findOneByCityName(cityName);
    }

    public City getCityCurrentWeatherByLonLat(String lon, String lat, boolean isShowing5day3hForecast) {
        System.out.println(lon);
        System.out.println(lat);
        System.out.println(isShowing5day3hForecast);
        return null;
    }

    public void deleteCity(String id) {
        cityRepository.deleteById(id);
    }

    public City getCity(String id) {
        City city = cityRepository.findOneById(id);
        if (city == null) {
            throw new RuntimeException("City not founded");
        }
        return city;
    }

    public City updateCity(String id, City city) {
        log.info("Updating city {}", city.getCityName());
        City dbCity = getCity(id);
        dbCity.setCityName(city.getCityName());
        dbCity.setMinTemperature(city.getMinTemperature());
        dbCity.setMaxTemperature(city.getMaxTemperature());
        dbCity.setWeatherCondition(city.getWeatherCondition());
        dbCity.setArea(city.getArea());
        dbCity.setPopulation(city.getPopulation());
        dbCity.setPressure(city.getPressure());
        dbCity.setHumidity(city.getHumidity());
        return cityRepository.save(dbCity);
    }
}
