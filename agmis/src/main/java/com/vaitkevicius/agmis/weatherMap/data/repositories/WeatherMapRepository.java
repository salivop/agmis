package com.vaitkevicius.agmis.weatherMap.data.repositories;

import com.vaitkevicius.agmis.weatherMap.data.db.WeatherMap;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * *
 * Created By Povilas 09/02/2019
 * *
 **/
@Repository
public interface WeatherMapRepository extends MongoRepository<WeatherMap, String> {
    WeatherMap findOneByName(String name);

    List<WeatherMap> findAllByName(String name);
}
