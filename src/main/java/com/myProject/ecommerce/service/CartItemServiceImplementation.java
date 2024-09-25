package com.myProject.ecommerce.service;

import com.myProject.ecommerce.Exception.CartItemException;
import com.myProject.ecommerce.Exception.UserException;
import com.myProject.ecommerce.Model.Cart;
import com.myProject.ecommerce.Model.CartItem;
import com.myProject.ecommerce.Model.Product;
import com.myProject.ecommerce.Model.User;
import com.myProject.ecommerce.Repository.CartItemRepository;
import com.myProject.ecommerce.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImplementation implements CartItemService{

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartRepository cartRepository;


    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setTotalPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setTotalDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
        CartItem createdCartItem = cartItemRepository.save(cartItem);

        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long Id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item = findCartItemById(Id);
        User user = userService.findUserById(item.getUserId());

        if(user.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setTotalPrice(cartItem.getQuantity()*cartItem.getProduct().getPrice());
            item.setTotalDiscountedPrice(cartItem.getQuantity()*cartItem.getProduct().getDiscountedPrice());

        }
        return cartItemRepository.save(item);

    }

    @Override
    public CartItem ifCartItemExists(Cart cart, Product product, String size, Long userId) {
        CartItem cartItem = cartItemRepository.doesCartItemExist(cart, product, size, userId);
        return cartItem;
    }


    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);
        User user = userService.findUserById(cartItem.getUserId());
        User reqUser = userService.findUserById(userId);

        if(user.getId().equals(reqUser.getId())){
            cartItemRepository.deleteById(cartItemId);
        }else{
            throw new UserException("You can't remove another user's cart Item");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);

        if(opt.isPresent()){
            return opt.get();
        }
        throw new CartItemException("Cart Item not found");
    }
}
