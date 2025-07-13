package com.scm.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;;


@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("PageController.home()");
        model.addAttribute("name","SpringBoot");
        model.addAttribute("Yt","Learn with Durgesh");
        model.addAttribute("github", "https://github.com/SwapnilKature25");
        return "home";
    }

    // about route
    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("isLogin", true);
        System.out.println("About page loading");
        return "about";
    }

    // services
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("services page loading");
        return "services";
    }
}
  