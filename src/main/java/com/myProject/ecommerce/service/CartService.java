package com.myProject.ecommerce.service;

import com.myProject.ecommerce.Exception.ProductException;
import com.myProject.ecommerce.Model.Cart;
import com.myProject.ecommerce.Model.User;
import com.myProject.ecommerce.Request.AddItemRequest;
import com.myProject.ecommerce.Request.DeleteItemRequest;

public interface CartService {
    public Cart createCart(User user);
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
    public String removeCartItem(Long userId, DeleteItemRequest req) throws ProductException;
    public Cart findUserCart(Long userId);
}
