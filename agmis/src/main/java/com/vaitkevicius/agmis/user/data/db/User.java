package com.vaitkevicius.agmis.user.data.db;

import com.vaitkevicius.agmis.weatherMap.data.db.WeatherMap;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * *
 * Created By Povilas 08/02/2019
 * *
 **/
@Document(collection = "users")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String homeLocation;

    @DBRef
    private List<WeatherMap> favoriteCities;

    private List<String> roles;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}
