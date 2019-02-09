package com.vaitkevicius.agmis.weatherMap.converters;

import com.vaitkevicius.agmis.common.converter.AbstractConverter;
import com.vaitkevicius.agmis.weatherMap.data.db.WeatherMap;
import com.vaitkevicius.agmis.weatherMap.data.dto.WeatherMapDto;

/**
 * *
 * Created By Povilas 09/02/2019
 * *
 **/
public class WeatherMapConverter extends AbstractConverter<WeatherMap, WeatherMapDto> {
    @Override
    public WeatherMapDto convertToDto(WeatherMap weatherMap) {
        return WeatherMapDto.builder()
                .id(weatherMap.getId())
                .name(weatherMap.getName())
                .country(weatherMap.getCountry())
                .coord(weatherMap.getCoord())
                .build();
    }

    @Override
    public WeatherMap convertToEntity(WeatherMapDto weatherMapDto) {
        return WeatherMap.builder()
                .id(weatherMapDto.getId())
                .name(weatherMapDto.getName())
                .country(weatherMapDto.getCountry())
                .coord(weatherMapDto.getCoord())
                .build();
    }
}
