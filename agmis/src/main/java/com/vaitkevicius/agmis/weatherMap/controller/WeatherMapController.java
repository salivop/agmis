package com.vaitkevicius.agmis.weatherMap.controller;

import com.vaitkevicius.agmis.common.UrlConst;
import com.vaitkevicius.agmis.weatherMap.services.WeatherMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * *
 * Created By Povilas 09/02/2019
 * *
 **/
@RestController
@RequestMapping(UrlConst.WEATHER_MAP)
public class WeatherMapController {

    @Autowired
    private WeatherMapService weatherMapService;

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveWeatherMap() throws Exception {
        weatherMapService.saveWeatherMap();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}