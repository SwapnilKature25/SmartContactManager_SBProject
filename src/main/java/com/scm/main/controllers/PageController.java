package com.scm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.main.entities.Providers;
import com.scm.main.entities.User;
import com.scm.main.forms.UserForm;
import com.scm.main.helpers.Message;
import com.scm.main.helpers.MessageType;
import com.scm.main.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class PageController {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    PageController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

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
    
    // this is login page
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }
    
    // this is registration controller - view
    // register page
    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm=new UserForm();
        // we can also add default data
        // userForm.setName("Swap");
        model.addAttribute("userForm",userForm);
        return "register";
    }

    // do registration/process registration
    // processing register ( Signup page)
    @RequestMapping(value="/do-Register", method=RequestMethod.POST)
    //Automatically userForm object will be created because of @ModelAttribute and the object fields will be mstching with userForm fields so it will be inserted into it.
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult  rBindingResult,HttpSession session){
        System.out.println("processing registration");
        // fetch form data
        // userForm
        System.out.println(userForm);
        // validate form data
        // Todo 
        if(rBindingResult.hasErrors()){
            return "register";
        }


        // save to database
        // UserService
        // took data from UserForm -> user and put into user
        // User user=User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://media.istockphoto.com/id/1393750072/vector/flat-white-icon-man-for-web-design-silhouette-flat-illustration-vector-illustration-stock.jpg?s=1024x1024&w=is&k=20&c=r--oPfS14d-ybe3adW-c_oy6q1tCz1c16SN8h5EdoKk=")
        // .build();   // we are not getting default values in table like SELF for provider
        
        User user=new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setEnabled(true);      // ✔ This allows the user to log in after registering
        user.setPassword(userForm.getPassword());
        // user.setPassword(passwordEncoder.encode(userForm.getPassword()));     // encoded in UserServiceImpl class
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://media.istockphoto.com/id/1393750072/vector/flat-white-icon-man-for-web-design-silhouette-flat-illustration-vector-illustration-stock.jpg?s=1024x1024&w=is&k=20&c=r--oPfS14d-ybe3adW-c_oy6q1tCz1c16SN8h5EdoKk=");
        user.setProvider(Providers.SELF); 

        // picture : recommended way is to not to hardcode it
        User saveUser=userService.saveUser(user);
        System.out.println("user saved");
        // message = "Registration successful"

        // add the message (alert) : to add message use Session/ to start session add HttpSession session
        Message message=Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message",message);

        // redirection to login page
        // return "redirect:/register";  // This is wrong because after registration you want the user to go to login page, not again to register.
        return "redirect:/login";
    }

    
    

}
  