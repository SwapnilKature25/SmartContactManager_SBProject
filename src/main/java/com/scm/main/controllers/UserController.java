package com.scm.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
public class UserController {

    // User dashboard page
    @RequestMapping(value="/dashboard")
    public String userDashboard() {  
        return "user/dashboard";
    }
    
    // User profile page
    @RequestMapping("/profile")
    public String userProfile() {
        return "user/profile"; 
    }
    

    // User add contacts page
    // User view contacts
    // User edit contact
    // User delete contact
    // User search contact
}
