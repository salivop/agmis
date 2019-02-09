package com.vaitkevicius.agmis.user.controllers;

import com.vaitkevicius.agmis.common.UrlConst;
import com.vaitkevicius.agmis.user.converters.UserConverter;
import com.vaitkevicius.agmis.user.data.dto.UserDto;
import com.vaitkevicius.agmis.user.services.UserService;
import com.vaitkevicius.agmis.weatherMap.data.dto.WeatherMapDto;
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
@RequestMapping(UrlConst.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto, BindingResult errors) throws Exception {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        userService.saveUser(new UserConverter().convertToEntity(userDto));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserDto userDto, BindingResult errors) throws Exception {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        userService.updateUser(id, new UserConverter().convertToEntity(userDto));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto getUser(@PathVariable String id) {
        return new UserConverter().convertToDto(userService.getUser(id));
    }


    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers() {
        return new UserConverter().toDto(userService.getAllUsers()
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user-filters", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getListUsersByFilters(
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("cities") String[] cities) {
        return new UserConverter().toDto(userService.getListUsersByFilters(firstName, lastName, cities)
        );
    }
}
