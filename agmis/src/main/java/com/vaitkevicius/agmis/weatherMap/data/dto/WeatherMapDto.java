package com.vaitkevicius.agmis.weatherMap.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * *
 * Created By Povilas 09/02/2019
 * *
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WeatherMapDto {

    @NotNull
    private String id;

    private String name;
    private String country;
    private List<String> coord;
}
