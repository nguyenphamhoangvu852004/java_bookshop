package com.gdu.dev_springboot_demo.database.OrderItems;

import java.util.UUID;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import com.gdu.dev_springboot_demo.model.OrderItems;

@Repository
public class OrderItemRepositoryImp implements IOrderItemRepository{

    private EntityManager em;

    public OrderItemRepositoryImp(EntityManager em){
        this.em=em;
    }

    @Override
    @Transactional
    public OrderItems create(OrderItems orderItems) {
        OrderItems o = orderItems;
        this.em.persist(o);
        this.em.flush();
        return o;
    }
}
