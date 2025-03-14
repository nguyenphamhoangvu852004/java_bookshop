package com.gdu.dev_springboot_demo.service.OrderItems;

import com.gdu.dev_springboot_demo.model.OrderItems;
import jakarta.servlet.http.HttpSession;

public interface IOrderItemService {
    OrderItems createOrderItem(OrderItems orderItems);
}
