package com.vaitkevicius.agmis.city.controllers;

import com.vaitkevicius.agmis.city.converters.CityConverter;
import com.vaitkevicius.agmis.city.converters.CityInfoByNameConverter;
import com.vaitkevicius.agmis.city.data.db.City;
import com.vaitkevicius.agmis.city.data.dto.CityDto;
import com.vaitkevicius.agmis.city.data.dto.CityInfoByNameDto;
import com.vaitkevicius.agmis.city.services.CityService;
import com.vaitkevicius.agmis.common.UrlConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * *
 * Created By Povilas 08/02/2019
 * *
 **/
@RestController
@RequestMapping(UrlConst.CITY)
public class CityController {

    @Autowired
    private CityService cityService;

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCity(@RequestBody CityDto cityDto, BindingResult errors) throws Exception {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        cityService.saveCity(new CityConverter().convertToEntity(cityDto));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCity( @PathVariable String id, @RequestBody CityDto cityDto, BindingResult errors) throws Exception {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        cityService.updateCity(id, new CityConverter().convertToEntity(cityDto));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityDto> getAllCities() {
        return new CityConverter().toDto(cityService.getAllCities()
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityDto> getCitiesByFilterParams(
            @QueryParam("area") String area,
            @QueryParam("population") String population) {
        return new CityConverter().toDto(cityService.getCitiesByFilterParams(area, population)
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/specificCity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CityInfoByNameDto getCityByName(
            @QueryParam("cityName") String cityName,
            @QueryParam("isShowing5day3hForecast") boolean isShowing5day3hForecast) {
        return new CityInfoByNameConverter().toDto(cityService.getCityByName(cityName, isShowing5day3hForecast)
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/currentWeather", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CityDto getCurrentWeatherByCoordinates(
            @QueryParam("lon") String lon,
            @QueryParam("lat") String lat,
            @QueryParam("isShowing5day3hForecast") boolean isShowing5day3hForecast) {
        return new CityConverter().toDto(cityService.getCityCurrentWeatherByLonLat(lon, lat, isShowing5day3hForecast)
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCity(@PathVariable String id) {
        cityService.deleteCity(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
