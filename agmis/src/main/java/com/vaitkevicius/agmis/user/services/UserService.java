package com.vaitkevicius.agmis.user.services;

import com.vaitkevicius.agmis.user.data.db.User;
import com.vaitkevicius.agmis.user.data.repositories.UserRepository;
import com.vaitkevicius.agmis.weatherMap.data.db.WeatherMap;
import com.vaitkevicius.agmis.weatherMap.data.repositories.WeatherMapRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * *
 * Created By Povilas 08/02/2019
 * *
 **/
@Service
@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WeatherMapRepository weatherMapRepository;

    public User saveUser(User user) {
        log.info("Creating new user with name");
        List<WeatherMap> favoriteCities = user.getFavoriteCities().stream()
                .map(city -> weatherMapRepository.findOneByName(city.getName())).collect(Collectors.toList());
        User newUser = User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .homeLocation(user.getHomeLocation())
                .favoriteCities(favoriteCities)

                .roles(Collections.singletonList("ADMIN"))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
        return userRepository.save(newUser);
    }

    public User updateUser(String id, User user) {
        log.info("Updating user by id {}", id);
        User dbUser = getUser(id);
        dbUser.setUsername(user.getUsername());
        dbUser.setPassword(user.getPassword());
        dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        dbUser.setFavoriteCities(user.getFavoriteCities());
        dbUser.setHomeLocation(user.getHomeLocation());
        dbUser.setFavoriteCities(user.getFavoriteCities());
        return userRepository.save(dbUser);
    }

    public User getUser(String id) {
        log.info("Getting user by id {}", id);
        User user = userRepository.findOneById(id);
        if (user == null) {
            throw new RuntimeException("User not founded");
        }
        return user;
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    public List<User> getListUsersByFilters(String firstName, String lastName, String[] favoriteCities ){
        List<WeatherMap> weatherMaps = new ArrayList<>();
        for(String city: favoriteCities){
            weatherMaps.add(weatherMapRepository.findOneByName(city));
        }
        return userRepository.findAllByFirstNameOrLastNameOrFavoriteCities(firstName,lastName, weatherMaps);
    }
}
