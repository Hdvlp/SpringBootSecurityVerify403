package com.springbootsecurityverify403.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springbootsecurityverify403.demo.model.User;
import com.springbootsecurityverify403.demo.model.UserPrincipal;
import com.springbootsecurityverify403.demo.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepository;

    public CustomUserDetailsService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

       List<String> roleArr = new ArrayList<>();
       String roleName = user.getRole();
       roleArr.add(roleName);
       Collections.singletonList(user.getRole());

        return new UserPrincipal(user, roleArr);
    }


}