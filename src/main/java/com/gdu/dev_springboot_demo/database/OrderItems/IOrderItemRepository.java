package com.gdu.dev_springboot_demo.database.OrderItems;

import java.util.List;
import java.util.UUID;

import com.gdu.dev_springboot_demo.model.OrderItems;

public interface IOrderItemRepository {
    OrderItems create(OrderItems orderItems);
    List<OrderItems> getOrderItemsByOrderId(UUID orderId);

}
