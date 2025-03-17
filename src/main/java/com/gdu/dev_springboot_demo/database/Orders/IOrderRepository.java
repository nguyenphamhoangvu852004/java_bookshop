package com.gdu.dev_springboot_demo.database.Orders;

import java.util.List;
import java.util.UUID;

import com.gdu.dev_springboot_demo.model.Orders;
public interface IOrderRepository {

    Orders creatOrders(UUID userId);
    List<Orders> getAllOrdersByUserId(UUID userId);
    List<Orders> getAllOrders();
    void updateOrders(UUID orderId, String status);
    List<Orders> filterOrdersByStatus(String status);

}

