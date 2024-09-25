package com.myProject.ecommerce.service;

import com.myProject.ecommerce.Model.OrderItem;
import org.springframework.stereotype.Service;


public interface OrderItemService {

    public OrderItem createOrderItem(OrderItem orderItem);

}
