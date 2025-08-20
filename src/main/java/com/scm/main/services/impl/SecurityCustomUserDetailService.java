package com.scm.main.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.main.entities.User;
import com.scm.main.repositories.UserRepo;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // aapne user ko load karana hai (write the code which brings user from database)
    //    return userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found with email : "+ username));
         User user = userRepo.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

          System.out.println("Loaded user from DB: " + user.getEmail());
        System.out.println("Encoded password from DB: " + user.getPassword());
         System.out.println("Found user: " + user.getEmail() + ", provider: " + user.getProvider());
        return user;

    }

}
