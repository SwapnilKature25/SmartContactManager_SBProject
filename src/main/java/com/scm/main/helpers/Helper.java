package com.scm.main.helpers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication){

        // agar humne email is password se login kiya hai to : hum email kaise nikalenge
        if(authentication instanceof OAuth2AuthenticationToken){

            var oauth2AuthenticationToken=(OAuth2AuthenticationToken)authentication;
            var clientId=oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User=(OAuth2User)authentication.getPrincipal();
            String username="";

            // sign with google kiya hai to kaise nikalenge
            if(clientId.equalsIgnoreCase("google")){
                System.out.println("Getting email from google");
                username=oauth2User.getAttribute("email").toString();
            }
            else if(clientId.equalsIgnoreCase("github")){
                // sign with github ...
                System.out.println("Getting email from github");
                username= oauth2User.getAttribute("email") !=null ?
                oauth2User.getAttribute("email").toString() : 
                oauth2User.getAttribute("login").toString()+"@gmail.com";
            }
            else if(clientId.equalsIgnoreCase("linkedin")){
                // sign with linkedin
                System.out.println("Getting email from linkedin");
            }
            
            // sign with facebook
            
            return username;
            
        } 
        else {
            System.out.println("Getting data from local database");
            return authentication.getName();
        }      
    }
}
