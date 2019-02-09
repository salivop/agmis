package com.vaitkevicius.agmis.user.data.repositories;

import com.vaitkevicius.agmis.user.data.db.User;
import com.vaitkevicius.agmis.weatherMap.data.db.WeatherMap;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * *
 * Created By Povilas 08/02/2019
 * *
 **/

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findOneById(String id);

    User findOneByUsername(String username);

    List<User> findAllByFirstNameOrLastNameOrFavoriteCities(String firstName, String lastName, List<WeatherMap> favoriteCities);
}