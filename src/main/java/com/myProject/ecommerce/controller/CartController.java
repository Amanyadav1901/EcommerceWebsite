package com.myProject.ecommerce.controller;

import com.myProject.ecommerce.Exception.ProductException;
import com.myProject.ecommerce.Exception.UserException;
import com.myProject.ecommerce.Model.Cart;
import com.myProject.ecommerce.Model.User;
import com.myProject.ecommerce.Request.AddItemRequest;
import com.myProject.ecommerce.Request.DeleteItemRequest;
import com.myProject.ecommerce.Response.ApiResponse;
import com.myProject.ecommerce.service.CartService;
import com.myProject.ecommerce.service.ProductService;
import com.myProject.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired private UserService userService;
    @Autowired private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization")String jwt)throws UserException{
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req , @RequestHeader("Authorization")String jwt)throws UserException, ProductException{
        User user = userService.findUserProfileByJwt((jwt));
        cartService.addCartItem(user.getId(), req);
        ApiResponse res = new ApiResponse();
        res.setMessage("Item added to cart successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
