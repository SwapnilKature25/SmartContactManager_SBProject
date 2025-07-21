package com.scm.main.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;


@Builder  /* for creating User in ProcessRegister() in pageController  */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
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
    // information
    private boolean enabled=false;
    private boolean emailVweified=false;
    private boolean phoneVweified=false;

    @Enumerated(value=EnumType.STRING)
    // Signup from Google, Self, Facebook, Twitter, LinkedIn, Github
    private Providers provider=Providers.SELF;
    private String providerUserId;

    // add more fields if needed
    // cascade means if user deletes then it's all contacts should be delete
    // fetch : till we don't need of their contacts till then we will not run the query in the database.
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    private List<Contact> contacts=new ArrayList<>();


}
