package com.example.bookreviewapi.controller;

import com.example.bookreviewapi.model.Review;
import com.example.bookreviewapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsByBook(@PathVariable Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @GetMapping("/user/{userId}")
    public List<Review> getReviewsByUser(@PathVariable Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
        return reviewRepository.findById(id)
                .map(review -> {
                    review.setRating(reviewDetails.getRating());
                    review.setComment(reviewDetails.getComment());
                    return ResponseEntity.ok(reviewRepository.save(review));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .map(review -> {
                    reviewRepository.delete(review);
                    return ResponseEntity.ok("Review deleted successfully.");
                })
                .orElse(ResponseEntity.status(404).body("Review not found"));
    }
    
   
}