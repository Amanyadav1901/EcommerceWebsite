package com.myProject.ecommerce.controller;

import com.myProject.ecommerce.Exception.ProductException;
import com.myProject.ecommerce.Exception.UserException;
import com.myProject.ecommerce.Model.Review;
import com.myProject.ecommerce.Model.User;
import com.myProject.ecommerce.Request.ReviewRequest;
import com.myProject.ecommerce.service.ReviewService;
import com.myProject.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    UserService userService;
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createRating(@RequestBody ReviewRequest req, @RequestHeader("Authorization") String jwt) throws ProductException, UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Review review = reviewService.createReview(req, user);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductRating(@PathVariable Long productId, @RequestHeader("Authorization")String jwt) throws ProductException {
        List<Review> reviews = reviewService.getAllReviews(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
