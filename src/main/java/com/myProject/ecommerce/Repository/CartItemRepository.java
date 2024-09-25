package com.myProject.ecommerce.Repository;

import com.myProject.ecommerce.Model.Cart;
import com.myProject.ecommerce.Model.CartItem;
import com.myProject.ecommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT c FROM CartItem c WHERE c.cart=:cart AND c.product=:product AND c.size=:size AND c.userId=:userId")
    public CartItem doesCartItemExist(@Param("cart") Cart cart, @Param("product") Product product, @Param("size") String size, @Param("userId") Long userId);

}
