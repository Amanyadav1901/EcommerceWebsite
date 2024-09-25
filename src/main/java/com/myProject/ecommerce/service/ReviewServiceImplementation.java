package com.myProject.ecommerce.service;

import com.myProject.ecommerce.Exception.ProductException;
import com.myProject.ecommerce.Model.Product;
import com.myProject.ecommerce.Model.Review;
import com.myProject.ecommerce.Model.User;
import com.myProject.ecommerce.Repository.ReviewRepository;
import com.myProject.ecommerce.Request.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    ProductService productService;

    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());
        Review review = new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);

    }

    @Override
    public List<Review> getAllReviews(Long productId) {
        return reviewRepository.getAllProductReviews(productId);
    }
}
