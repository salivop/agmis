package com.vaitkevicius.agmis.auth.data.dto;

import com.vaitkevicius.agmis.user.data.db.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * *
 * Created By Povilas 09/02/2019
 * *
 **/
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    private User user;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<String> roles = null;
        if (user != null) {
            roles = user.getRoles();
        }

        return CollectionUtils.emptyIfNull(roles).stream()
                .map(r -> new SimpleGrantedAuthority(r))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        if (this.user == null) {
            return null;
        }
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.user.isEnabled();
    }
}