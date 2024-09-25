package com.myProject.ecommerce.controller;

import com.myProject.ecommerce.Exception.CartItemException;
import com.myProject.ecommerce.Exception.UserException;
import com.myProject.ecommerce.Model.CartItem;
import com.myProject.ecommerce.Model.User;
import com.myProject.ecommerce.Response.ApiResponse;
import com.myProject.ecommerce.service.CartItemService;
import com.myProject.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId, @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId() , cartItemId);
        ApiResponse res = new ApiResponse();
        res.setMessage("Removed Item From the Cart");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable Long cartItemId, @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);
        CartItem cartItem = cartItemService.findCartItemById(cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Updated Cart Item");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
