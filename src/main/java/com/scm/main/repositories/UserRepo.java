package com.scm.main.repositories;
// Repositories are for database interaction

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.main.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    // extra methods db related operations
    // custom query methods

    // custom finder methods  : It's implementation automatically will be done by data jpa
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String password);
}
