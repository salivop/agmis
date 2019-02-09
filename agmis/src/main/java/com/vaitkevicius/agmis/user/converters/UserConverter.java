package com.vaitkevicius.agmis.user.converters;

import com.vaitkevicius.agmis.common.converter.AbstractConverter;
import com.vaitkevicius.agmis.user.data.db.User;
import com.vaitkevicius.agmis.user.data.dto.UserDto;

/**
 * *
 * Created By Povilas 08/02/2019
 * *
 **/

public class UserConverter extends AbstractConverter<User, UserDto> {
    @Override
    public UserDto convertToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .homeLocation(user.getHomeLocation())
                .favoriteCities(user.getFavoriteCities())
                .roles(user.getRoles())
                .build();
    }

    @Override
    public User convertToEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .homeLocation(userDto.getHomeLocation())
                .favoriteCities(userDto.getFavoriteCities())
                .roles(userDto.getRoles())
                .build();
    }
}