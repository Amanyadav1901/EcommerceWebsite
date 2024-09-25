package com.myProject.ecommerce.service;

import com.myProject.ecommerce.Exception.ProductException;
import com.myProject.ecommerce.Model.Review;
import com.myProject.ecommerce.Model.User;
import com.myProject.ecommerce.Request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    public Review createReview(ReviewRequest req, User user) throws ProductException;
    public List<Review> getAllReviews(Long productId);
}
