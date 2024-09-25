package com.myProject.ecommerce.Repository;

import com.myProject.ecommerce.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
