package com.scm.main.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {
    @NotBlank(message="Username is required")
    @Size(min=3 ,message="Min 3 Characters is required")
    private String name;

    @Email(message="Invalid Email Address")
    @NotBlank(message="Email is required")
    // @Pattern("")  // if we want to use any pattern paste here
    private String email;

    @NotBlank(message="Password is required")
    @Size(min=6, message="Min 6 Characters is required")
    private String password;

    @NotBlank(message="About is required")
    private String about;

    @Size(min=8, max=12, message="Invalid Phone Number")
    private String phoneNumber;
    
    
}
