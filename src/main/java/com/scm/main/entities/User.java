package com.scm.main.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
@Builder  /* for creating User in ProcessRegister() in pageController  */
public class User implements UserDetails{
    @Id
    @Column(name="user_id")
    private String userId;
    @Column(name="user_name", nullable=false)
    private String name;
    @Column(unique=true , nullable=false)
    private String email;
    private String password;
    @Column(length=5000)
    private String about;
    @Column(length=5000)
    private String profilePic;
    private String phoneNumber;

    @Getter(value=AccessLevel.NONE)
    // information
    private boolean enabled=true;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    @Enumerated(value=EnumType.STRING)
    // Signup from Google, Self, Facebook, Twitter, LinkedIn, Github
    private Providers provider=Providers.SELF;
    private String providerUserId;

    // add more fields if needed
    // cascade means if user deletes then it's all contacts should be delete
    // fetch : till we don't need of their contacts till then we will not run the query in the database.
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    private List<Contact> contacts=new ArrayList<>();

    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> roleList=new ArrayList<>();    // it will save all role of users
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // we need this when we want to know which user has what role
        // listOfRole -> ListOfGrantedAuthority
        // List of roles[USER, ADMIN,..] Converted to Collection of SimpleGrantedAuthority[roles{ADMIN,USER}]
        Collection<SimpleGrantedAuthority> roles=roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    // for this project : email id hai wahi humara username
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    


}
