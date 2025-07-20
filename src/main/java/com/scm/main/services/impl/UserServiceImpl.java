package com.scm.main.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.main.entities.User;
import com.scm.main.helpers.ResourceNotFoundException;
import com.scm.main.repositories.UserRepo;
import com.scm.main.services.UserService;

package com.scm.main.helpers.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    private Logger log=LoggerFactory.getLogger(this.getClass());

    // ctrl + . for adding unimplemented methods
    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        userRepo.findById(user.getUserId()).orElseThrow(exceptionSupplier)(()-> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public boolean isUserExist(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isUserExist'");
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isUserExistByEmail'");
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

}
