package com.myProject.ecommerce.service;

import com.myProject.ecommerce.Exception.ProductException;
import com.myProject.ecommerce.Model.Product;
import com.myProject.ecommerce.Model.Rating;
import com.myProject.ecommerce.Model.User;
import com.myProject.ecommerce.Repository.RatingRepository;
import com.myProject.ecommerce.Request.RatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductRating(Long productId) throws ProductException {
        return ratingRepository.getAllProductRating(productId);
    }
}
