package com.scm.main.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.main.entities.User;
import com.scm.main.helpers.AppConstants;
import com.scm.main.helpers.ResourceNotFoundException;
import com.scm.main.repositories.UserRepo;
import com.scm.main.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    // ctrl + . for adding unimplemented methods
    @Override
    public User saveUser(User user) {
        // User id : have to generate dynamically(Auto-generate)
        String userId=UUID.randomUUID().toString();
        user.setUserId(userId);
        // when we want to endcode password
        // user.setPassword(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set the user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));   // (List.of("ROLE_USER") can do hardcoded so not recommended

        logger.info(user.getProvider().toString());
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2=userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        // update user2 from user
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        // save the user in database
        User save=userRepo.save(user2);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        User user2=userRepo.findById(id).orElseThrow( ()-> new ResourceNotFoundException("User not found"));
        userRepo.delete(user2);

    }

    @Override
    public boolean isUserExist(String userId) {
        User user2=userRepo.findById(userId).orElse(null);
        return (user2!=null) ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user=userRepo.findByEmail(email).orElse(null);
        return (user!=null) ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
       return userRepo.findAll();
    }

}
