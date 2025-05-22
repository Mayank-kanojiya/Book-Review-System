package com.example.bookreviewapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BookReviewApiApplication {
	
	@RequestMapping("/message")
	public String getMessage() {
		return "welcome";
	}

	public static void main(String[] args) {
		SpringApplication.run(BookReviewApiApplication.class, args);
	}

}
