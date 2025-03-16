package com.gdu.dev_springboot_demo.service.OrderItems;

import com.gdu.dev_springboot_demo.model.OrderItems;
import com.gdu.dev_springboot_demo.model.Orders;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface IOrderItemService {
    OrderItems createOrderItem(OrderItems orderItems);
}
