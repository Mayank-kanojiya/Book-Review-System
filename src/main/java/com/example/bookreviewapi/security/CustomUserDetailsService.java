package com.example.bookreviewapi.security;

import com.example.bookreviewapi.model.User;
import com.example.bookreviewapi.repository.UserRepository; // Ensure this repository is created
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService { // Class implementing UserDetailsService

    @Autowired
    private UserRepository userRepository; // Inject the UserRepository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // Correct method name
        // Assuming you want to authenticate by email
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User  not found with username: " + username);
        }
        return user; // Ensure User implements UserDetails
    }
}