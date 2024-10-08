package com.myProject.ecommerce.service;

import com.myProject.ecommerce.Exception.OrderException;
import com.myProject.ecommerce.Model.Address;
import com.myProject.ecommerce.Model.Order;
import com.myProject.ecommerce.Model.User;

import java.util.List;

public interface OrderService {

    public Order createOrder(User user, Address shippingAddress);
    public Order findOrderById(Long orderId) throws OrderException;
    public List<Order> userOrderHistory(Long orderId);
    public Order placedOrder(Long orderId) throws OrderException;
    public Order canceledOrder(Long orderId) throws OrderException;
    public Order deliveredOrder(Long orderId) throws OrderException;
    public Order shippedOrder(Long orderId) throws OrderException;
    public Order confirmedOrder(Long orderId) throws OrderException;
    public List<Order> getAllOrders();
    public void deleteOrder(Long orderId) throws OrderException;


}
