package com.vaitkevicius.agmis.weatherMap.services;

import com.vaitkevicius.agmis.weatherMap.data.db.WeatherMap;
import com.vaitkevicius.agmis.weatherMap.data.repositories.WeatherMapRepository;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * *
 * Created By Povilas 09/02/2019
 * *
 **/
@Service
@Log4j2
public class WeatherMapService {

    @Autowired
    private WeatherMapRepository weatherMapRepository;

    public void saveWeatherMap() {
        readWeatherMatJson();
    }

    private void readWeatherMatJson() {
        log.info("Reading and saving weather map");
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("city.list.json"));
            JSONArray jsonArray = (JSONArray) obj;
            jsonArray.stream().forEach(o -> {
                        JSONObject jsonObject = (JSONObject) o;
                        WeatherMap weatherMap = new WeatherMap();
                        weatherMap.setId(jsonObject.get("id").toString());
                        weatherMap.setName(jsonObject.get("name").toString());
                        weatherMap.setCountry(jsonObject.get("country").toString());

                        JSONObject coord = (JSONObject) jsonObject.get("coord");
                        List<String> lonLatArray = new ArrayList<>();
                        lonLatArray.add(coord.get("lat").toString());
                        lonLatArray.add(coord.get("lon").toString());
                        weatherMap.setCoord(lonLatArray);
                        saveWeatherMap(weatherMap);
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WeatherMap saveWeatherMap(WeatherMap weatherMap) {
        log.info("Saving weather map by city {}", weatherMap.getName());
        WeatherMap newWeatherMap = WeatherMap.builder()
                .id(weatherMap.getId())
                .name(weatherMap.getName())
                .country(weatherMap.getCountry())
                .coord(weatherMap.getCoord())
                .build();
        return weatherMapRepository.save(newWeatherMap);
    }

    public String getCityId(String cityName) {
        log.info("Getting city by name {}", cityName);
        String id = weatherMapRepository.findOneByName(cityName).getId();

        if (id == null) {
            throw new RuntimeException("City not found");
        }
        return id;
    }
}
