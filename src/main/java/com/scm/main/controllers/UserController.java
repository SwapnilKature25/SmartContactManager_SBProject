package com.scm.main.controllers;

import java.security.Principal;

import com.scm.main.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.main.entities.User;
import com.scm.main.helpers.Helper;


@Controller
@RequestMapping("/user")
public class UserController {

    
    private Logger logger=LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private final UserService userService;
    
    UserController(UserService userService) {
        this.userService = userService;
    }

   

    // User dashboard page
    @RequestMapping(value="/dashboard", method= {RequestMethod.GET, RequestMethod.POST})
    public String userDashboard() {  
        return "user/dashboard";
    }
    
    // User profile page
    @RequestMapping("/profile")
    public String userProfile(Model model,Authentication authentication) {
       
        return "user/profile"; 
    }
    

    // User add contacts page
    // User view contacts
    // User edit contact
    // User delete contact
    // User search contact
}
