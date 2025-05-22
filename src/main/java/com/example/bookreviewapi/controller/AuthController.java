package com.example.bookreviewapi.controller;

import com.example.bookreviewapi.model.Book;
import com.example.bookreviewapi.model.Review;
import com.example.bookreviewapi.model.User;
import com.example.bookreviewapi.security.JwtUtil; // Correct import for JwtUtil
import com.example.bookreviewapi.repository.BookRepository;
import com.example.bookreviewapi.repository.ReviewRepository;
import com.example.bookreviewapi.repository.UserRepository;
import com.example.bookreviewapi.DTO.AuthRequest;
import com.example.bookreviewapi.DTO.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final String Book_id = null;

	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }

        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
    
    //Admin only End point
    @PutMapping("/users/{id}/role")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // Only admins can access
    public ResponseEntity<?> updateUserRole(@PathVariable Long id, @RequestBody Set<String> roles) {
        return userRepository.findById(id)
            .map(user -> {
                user.setRoles(roles);
                userRepository.save(user);
                return ResponseEntity.ok("Roles updated");
            })
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }

    
    // Register Endpoint
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign default role as ROLE_USER if no roles are provided
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Set<String> roles = new HashSet<>();
            roles.add("ROLE_USER");
            user.setRoles(roles);
        }

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
   
    
//    @PostMapping("/book/{bookId}")
//    public ResponseEntity<?> addReviewToBook(
//            @PathVariable Long bookId,
//            @RequestBody Review review,
//            @AuthenticationPrincipal UserDetails userDetails
//    ) {
//        // Assuming your UserDetails holds user ID or username
//        String username = userDetails.getUsername(); // You can fetch user by username
//        User user = userRepository.findByUserName(username).orElseThrow();
//        Long id=userDetails.
//		Book book = bookRepository.findById(id).orElseThrow(()->new RunTimeException("Book not found"));
//        review.setBook(new Book());
//        review.setUser(user);
//
//        Review saved = reviewRepository.save(review);
//        return ResponseEntity.ok(saved);
//    }
//    @PostMapping("/book/{bookId}")
//    public ResponseEntity<?> addReviewToBook(
//            @PathVariable Long bookId,
//            @RequestBody Review review,
//            @AuthenticationPrincipal UserDetails userDetails) {
//
//        String name = userDetails.getUsername();
//        User user = userRepository.findByUserName(name)
//        	    .orElseThrow();
//
//
//        Book book = bookRepository.findById(bookId).orElseThrow();  // ✅ Actual entity
//
//        review.setBook(book); // ✅ avoid transient state
//        review.setUser(user);
//
//        Review saved = reviewRepository.save(review);
//        return ResponseEntity.ok(saved);
//    }


}
