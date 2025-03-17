package com.gdu.dev_springboot_demo.service.Orders;

import com.gdu.dev_springboot_demo.model.Orders;

import java.util.List;
import java.util.UUID;

public interface IOrderService {

    Orders createOrder(UUID userId);
    List<Orders> getAllOrdersByUserId(UUID userId);
    List<Orders> getAllOrders();
    void updateOrder(UUID userId, String status);
    List<Orders> filterOrdersByStatus(String status);

}
