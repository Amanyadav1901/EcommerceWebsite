package com.myProject.ecommerce.controller;

import com.myProject.ecommerce.Exception.OrderException;
import com.myProject.ecommerce.Model.Order;
import com.myProject.ecommerce.Response.ApiResponse;
import com.myProject.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrdersHandler(){
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<List<Order>>(orders , HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> ConfirmedOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException{
        Order order = orderService.confirmedOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order> ShipOrderHandler(@PathVariable Long OrderId, @RequestHeader("Authorization") String jwt) throws OrderException{
        Order order = orderService.shippedOrder(OrderId);
        return new ResponseEntity<>(order , HttpStatus.OK);
    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order> DeliverOrderHandler(@PathVariable Long OrderId, @RequestHeader("Authorization") String jwt) throws OrderException{
        Order order = orderService.deliveredOrder(OrderId);
        return new ResponseEntity<>(order , HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> CancelOrderHandler(@PathVariable Long OrderId, @RequestHeader("Authorization") String jwt) throws OrderException{
        Order order = orderService.canceledOrder(OrderId);
        return new ResponseEntity<>(order , HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse> DeleteOrderHandler(@PathVariable Long OrderId, @RequestHeader("Authorization") String jwt) throws OrderException{
        orderService.deleteOrder(OrderId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Order deleted successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res , HttpStatus.OK);
    }
}
