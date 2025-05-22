package com.example.bookreviewapi.repository;

import com.example.bookreviewapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); // Assuming you want to find users by email
    //User findByUserName(String name);
}