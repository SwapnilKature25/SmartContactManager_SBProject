package com.scm.main.controllers;
// this class method will execute for every request

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.main.entities.User;
import com.scm.main.helpers.Helper;
import com.scm.main.services.UserService;

@ControllerAdvice   // because of this whatever method we write here it will be executed for every request
public class RootController {

    private Logger logger=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model,Authentication authentication){
        if(authentication==null){
            return;
        }
        System.out.println("Adding logged in user information to the model");
        // String name = principal.getName();   // instead of it use helper class method
        String username=Helper.getEmailOfLoggedInUser(authentication);
        
        // printing user using logger and sysout stmts
        logger.info("User logged in : {}",username);

        // database se data ko fetch : get user from DB
        User user=userService.getUserByEmail(username);
        System.out.println("User name : "+user.getName());
        System.out.println("User email : "+user.getEmail());
        model.addAttribute("loggedInUser", user);
       

        // System.out.println("User profile : "+username);

    }
}
