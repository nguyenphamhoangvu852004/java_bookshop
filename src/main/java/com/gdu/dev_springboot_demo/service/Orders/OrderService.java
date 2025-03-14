package com.gdu.dev_springboot_demo.service.Orders;

import java.util.List;
import java.util.UUID;

import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import com.gdu.dev_springboot_demo.database.Orders.IOrderRepository;
import com.gdu.dev_springboot_demo.model.Orders;


@Service
public class OrderService  implements IOrderService{
    private IOrderRepository orderRepository;

    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Orders createOrder(UUID userId) {
        if (userId == null) {
            throw new RuntimeException("User khong ton tai");
        }
        return this.orderRepository.creatOrders(userId);
        
    }

    @Override
    public List<Orders> getAllOrdersByUserId(UUID userId) {
        return this.orderRepository.getAllOrdersByUserId(userId);
    }

}
