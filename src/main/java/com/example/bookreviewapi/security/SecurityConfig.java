package com.example.bookreviewapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//	public class PasswordHashGenerator {
//	    public static void main(String[] args) {
//	        System.out.println(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("john"));
//	    }
//	}
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/books/**").permitAll()
                .requestMatchers("/api/reviews/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/api/users/**").permitAll()
                .requestMatchers(HttpMethod.DELETE,"/api/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS));

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}