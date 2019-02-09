package com.vaitkevicius.agmis.user.data.dto;

import com.vaitkevicius.agmis.weatherMap.data.db.WeatherMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * *
 * Created By Povilas 08/02/2019
 * *
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {

    @NotNull
    private String id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String homeLocation;

    @NotEmpty
    private List<WeatherMap> favoriteCities;

    private List<String> roles;
}