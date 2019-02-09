package com.vaitkevicius.agmis.city.data.repositories;

import com.vaitkevicius.agmis.city.data.db.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * *
 * Created By Povilas 08/02/2019
 * *
 **/
@Repository
public interface CityRepository extends MongoRepository<City, String> {

    List<City> findAllByAreaOrPopulation(String area, String population);

    City findOneByCityName(String cityName);

    City findOneById(String id);
}
