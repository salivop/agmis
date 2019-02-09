package com.vaitkevicius.agmis.auth.services;

import com.vaitkevicius.agmis.auth.data.dto.CustomUserDetails;
import com.vaitkevicius.agmis.user.data.db.User;
import com.vaitkevicius.agmis.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * *
 * Created By Povilas 09/02/2019
 * *
 **/
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("error, user not found");
        }
        return new CustomUserDetails(user);
    }
}