package com.gdu.dev_springboot_demo.service.OrderItems;

import com.gdu.dev_springboot_demo.database.OrderItems.IOrderItemRepository;
import com.gdu.dev_springboot_demo.model.OrderItems;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImp implements IOrderItemService {

    private IOrderItemRepository orderItemRepository;

    public OrderItemServiceImp(IOrderItemRepository iOrderItemRepository){
        this.orderItemRepository = iOrderItemRepository;
    }


    @Override
    public OrderItems createOrderItem(OrderItems orderItems) {
        return this.orderItemRepository.create(orderItems);
    }
}
