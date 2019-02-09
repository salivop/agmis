package com.vaitkevicius.agmis.city.converters;

import com.vaitkevicius.agmis.city.data.db.City;
import com.vaitkevicius.agmis.city.data.dto.CityDto;
import com.vaitkevicius.agmis.city.data.dto.CityInfoByNameDto;
import com.vaitkevicius.agmis.common.converter.AbstractConverter;

/**
 * *
 * Created By Povilas 08/02/2019
 * *
 **/
public class CityInfoByNameConverter extends AbstractConverter<City, CityInfoByNameDto> {
    @Override
    public CityInfoByNameDto convertToDto(City city) {
        return CityInfoByNameDto.builder()
                .id(city.getId())
                .cityName(city.getCityName())
                .minTemperature(city.getMinTemperature())
                .maxTemperature(city.getMaxTemperature())
                .weatherCondition(city.getWeatherCondition())
                .pressure(city.getPressure())
                .humidity(city.getHumidity())
                .build();
    }

    @Override
    public City convertToEntity(CityInfoByNameDto cityInfoByNameDto) {
        return City.builder()
                .id(cityInfoByNameDto.getId())
                .cityName(cityInfoByNameDto.getCityName())
                .minTemperature(cityInfoByNameDto.getMinTemperature())
                .maxTemperature(cityInfoByNameDto.getMaxTemperature())
                .weatherCondition(cityInfoByNameDto.getWeatherCondition())
                .pressure(cityInfoByNameDto.getPressure())
                .humidity(cityInfoByNameDto.getHumidity())
                .build();
    }
}