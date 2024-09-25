package com.myProject.ecommerce.service;

import com.myProject.ecommerce.Exception.ProductException;
import com.myProject.ecommerce.Model.Cart;
import com.myProject.ecommerce.Model.CartItem;
import com.myProject.ecommerce.Model.Product;
import com.myProject.ecommerce.Model.User;
import com.myProject.ecommerce.Repository.CartRepository;
import com.myProject.ecommerce.Request.AddItemRequest;
import com.myProject.ecommerce.Request.DeleteItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;


    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(req.getProductId());

        CartItem isPresent = cartItemService.ifCartItemExists(cart,product, req.getSize(), userId);

        if(isPresent == null){
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);

            double price  = req.getQuantity()*product.getDiscountedPrice();
            cartItem.setTotalDiscountedPrice(price);
            cartItem.setSize(req.getSize());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
        }

        return "Item Added to Cart";
    }

    @Override
    public String removeCartItem(Long userId, DeleteItemRequest req) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(req.getProductId());
        CartItem isPresent = cartItemService.ifCartItemExists(cart,product, req.getSize(), userId);

        if(isPresent == null){
            return "item do not exist in cart";
        }

        return "item removed from cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);

        double totalPrice = 0;
        double totalDiscountedPrice = 0;
        int totalItem=0;

        for(CartItem cartItem: cart.getCartItems()){
            totalPrice += cartItem.getTotalPrice();
            totalDiscountedPrice += cartItem.getTotalDiscountedPrice();
            totalItem += cartItem.getQuantity();
        }
        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setDiscount((int)((totalPrice - totalDiscountedPrice)/totalPrice)*100);

        return cartRepository.save(cart);
    }
}
