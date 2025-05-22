package com.example.bookreviewapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password; // Store hashed password

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles; // e.g., "ROLE_USER", "ROLE_ADMIN"

    // --- UserDetails Interface Methods ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return this.email; // you're authenticating using email
    }
    @Override
    public String getPassword() {
        return this.password; // you're authenticating using email
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	public User orElseThrow() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
