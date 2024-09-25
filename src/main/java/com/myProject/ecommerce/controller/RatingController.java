package com.myProject.ecommerce.controller;

import com.myProject.ecommerce.Exception.ProductException;
import com.myProject.ecommerce.Exception.UserException;
import com.myProject.ecommerce.Model.Rating;
import com.myProject.ecommerce.Model.User;
import com.myProject.ecommerce.Request.RatingRequest;
import com.myProject.ecommerce.service.RatingService;
import com.myProject.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
    @Autowired
    private UserService userService;
    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req, @RequestHeader("Authorization") String jwt) throws ProductException, UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Rating rating = ratingService.createRating(req, user);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductRating(@PathVariable Long productId, @RequestHeader("Authorization")String jwt) throws ProductException, UserException {

        List<Rating> rating = ratingService.getProductRating(productId);

        return new ResponseEntity<>(rating, HttpStatus.OK);
    }
}
