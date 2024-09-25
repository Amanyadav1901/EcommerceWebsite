package com.myProject.ecommerce.service;


import com.myProject.ecommerce.Exception.ProductException;
import com.myProject.ecommerce.Model.Rating;
import com.myProject.ecommerce.Model.User;
import com.myProject.ecommerce.Request.RatingRequest;

import java.util.List;

public interface RatingService {
    public Rating createRating (RatingRequest req , User user) throws ProductException;
    public List<Rating> getProductRating(Long productId) throws ProductException;
}
