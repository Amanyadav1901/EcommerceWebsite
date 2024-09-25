package com.myProject.ecommerce.service;

import com.myProject.ecommerce.Exception.CartItemException;
import com.myProject.ecommerce.Exception.UserException;
import com.myProject.ecommerce.Model.Cart;
import com.myProject.ecommerce.Model.CartItem;
import com.myProject.ecommerce.Model.Product;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(Long userId, Long Id, CartItem cartItem) throws CartItemException, UserException;
    public CartItem ifCartItemExists(Cart cart, Product product, String size, Long userId);
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
