package com.gdu.dev_springboot_demo.database.Orders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.gdu.dev_springboot_demo.model.Users;
import org.springframework.stereotype.Repository;

import com.gdu.dev_springboot_demo.database.OrderItems.IOrderItemRepository;
import com.gdu.dev_springboot_demo.model.Orders;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@Repository
public class OrderRepository implements IOrderRepository {
    private EntityManager em;
    public OrderRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    @Transactional
    public Orders creatOrders(UUID userId) {
        Users user = this.em.find(Users.class, userId);
        if (user == null){
            throw new RuntimeException("User khong ton tai");
        }

        Orders orders = new Orders();
        orders.setUsers(user);
        orders.setStatus(Orders.PENDING);
        orders.setListOrderItems(new ArrayList<>());

        em.persist(orders);
        em.flush();
        return orders;
    }

    @Override
    @Transactional
    public List<Orders> getAllOrdersByUserId(UUID userId) {
        Users user = this.em.find(Users.class, userId);
        if (user == null){
            throw new RuntimeException("User khong ton tai");
        }
        String sql = "SELECT o FROM Orders o WHERE o.users.id = :userId";
        List<Orders> listOrders = this.em.createQuery(sql, Orders.class).setParameter("userId", userId).getResultList();
        listOrders.forEach(System.out::println);
        System.out.println("Kich thuoc cua listOrders: "+listOrders.size());
        return listOrders;
    }

}
