package com.vaitkevicius.agmis.city.converters;

import com.vaitkevicius.agmis.city.data.db.City;
import com.vaitkevicius.agmis.city.data.dto.CityDto;
import com.vaitkevicius.agmis.common.converter.AbstractConverter;

/**
 * *
 * Created By Povilas 08/02/2019
 * *
 **/
public class CityConverter extends AbstractConverter<City, CityDto> {
    @Override
    public CityDto convertToDto(City city) {
        return CityDto.builder()
                .id(city.getId())
                .cityName(city.getCityName())
                .minTemperature(city.getMinTemperature())
                .maxTemperature(city.getMaxTemperature())
                .weatherCondition(city.getWeatherCondition())
                .area(city.getArea())
                .population(city.getPopulation())
                .pressure(city.getPressure())
                .humidity(city.getHumidity())
                .build();
    }

    @Override
    public City convertToEntity(CityDto cityDto) {
        return City.builder()
                .id(cityDto.getId())
                .cityName(cityDto.getCityName())
                .minTemperature(cityDto.getMinTemperature())
                .maxTemperature(cityDto.getMaxTemperature())
                .weatherCondition(cityDto.getWeatherCondition())
                .area(cityDto.getArea())
                .population(cityDto.getPopulation())
                .pressure(cityDto.getPressure())
                .humidity(cityDto.getHumidity())
                .build();
    }
}