package com.springbootsecurityverify403.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    private User user;
    private List<String> rolesArr = new ArrayList<>();

    public UserPrincipal(User user) {
        this.user = user;
    }

    public UserPrincipal(User user, List<String> rolesArr) {
        this.user = user;
        this.rolesArr = rolesArr;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String roleName : this.rolesArr){
            authorities.add(new SimpleGrantedAuthority(roleName));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // email is the username
        return user.getEmail();
    }

}
