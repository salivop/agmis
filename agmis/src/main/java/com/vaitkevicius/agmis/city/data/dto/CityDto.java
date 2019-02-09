package com.vaitkevicius.agmis.city.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * *
 * Created By Povilas 08/02/2019
 * *
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CityDto {

    @NotNull
    private String id;

    private String cityName;

    private String minTemperature;
    private String maxTemperature;
    private String weatherCondition;

    private String area;
    private String population;

    private String pressure;
    private String humidity;
}