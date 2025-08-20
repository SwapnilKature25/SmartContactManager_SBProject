package com.scm.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.main.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {
    // create user and login using java code within memory service
    // @Bean
    // public UserDetailsService userDetailsService(){
    //     UserDetails user1=User
    //     .withDefaultPasswordEncoder()
    //     .username("admin123")
    //     .password("admin123")
    //     .roles("ADMIN","USER")
    //     .build(); 

    //     UserDetails user2=User
    //     .withDefaultPasswordEncoder()
    //     .username("user123")
    //     .password("user123")
    //     // .roles() 
    //     .build();
    //     var inMemoryUserDetailsManager=new InMemoryUserDetailsManager(user1,user2);
    //     return inMemoryUserDetailsManager;
    // }


    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Autowired
    public OAuthenticationSuccessHandler handler;

    // Configuration of authentication provider for spring security.
    // configure/setup username and password through database 
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        // User detail service ka object
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // password encoder ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        // configuration
        // authorizeHttpRequests will make /home as public url and all others remains protected
        // urls configure kiye hai ki koun se public rahenge aur kon se private rahenge
        httpSecurity.authorizeHttpRequests( (authorize)->{
            // authorize.requestMatchers("/home","/register").permitAll();
            authorize.requestMatchers("/login", "/register", "/authenticate").permitAll();
            // we want to protect some url only
            // those url which starts from user (/user/...) we will make it authenticated(verified identity)
            authorize.requestMatchers("/user/**").authenticated();  // this url becomes protected we need to login to access it.
            // and all others request make it permit all
            authorize.anyRequest().permitAll();
        });

        // form default login
        // agar hume kuch bhi change karna hua to hum yha ayenge : form login se related 
        // httpSecurity.formLogin(Customizer.withDefaults());  // customizer from security package

        httpSecurity.formLogin( formLogin -> {
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/profile");
            // formLogin.failureForwardUrl("/login?error=true");
            // formLogin.defaultSuccessUrl("/user/dashboard", true);
            formLogin.failureUrl("/login?error=true");

            // formLogin.defaultSuccessUrl("/home");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
            // formLogin.failureHandler(new AuthenticationFailureHandler() {

            //     @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            //             AuthenticationException exception) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
            //     }
                
            // });

            // formLogin.successHandler(new AuthenticationSuccessHandler() {

            //     @Override
            //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            //             Authentication authentication) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
            //     }
                
            // });
        });

        //  default logout    // you also disabled CSRF, which is OK for now (but not recommended for production).
        httpSecurity.csrf(AbstractHttpConfigurer::disable);  // CSRF is disabled
        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });


        // we need oauth2 all default property
        // httpSecurity.oauth2Login(Customizer.withDefaults());

        // oauth configurations
        // customize oauth2 
        httpSecurity.oauth2Login( oauth-> {
            oauth.loginPage("/login");
            oauth.successHandler(handler);
        });

        // httpSecurity.authenticationProvider(authenticationProvider());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    

}
