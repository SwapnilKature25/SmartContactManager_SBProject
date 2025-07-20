package com.scm.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.main.forms.UserForm;


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

    // contact page
    @GetMapping("/contact")
    public String getMethodName() {
        return new String("contact");
    }
    
    // login page
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }
    
    // register page
    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm=new UserForm();
        // we can also add default data
        // userForm.setName("Swap");
        model.addAttribute("userForm",userForm);
        return "register";
    }


    // processing register ( Signup page)
    @RequestMapping(value="/do-Register", method=RequestMethod.POST)
    //Automatically userForm object will be created because of @ModelAttribute and the object fields will be mstching with userForm fields so it will be inserted into it.
    public String processRegister(@ModelAttribute UserForm userForm){
        System.out.println("processing registration");
        // fetch form data
        // userForm
        System.out.println(userForm);
        // validate form data
        // Todo 

        // save to database
        // UserService 

        // message = "Registration successful"
        // redirection to login page
        return "redirect:/register";
    }

    
    

}
  