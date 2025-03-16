package com.gdu.dev_springboot_demo.controller;

import java.util.List;

import com.gdu.dev_springboot_demo.model.*;
import com.gdu.dev_springboot_demo.service.OrderItems.IOrderItemService;
import com.gdu.dev_springboot_demo.service.Products.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.dev_springboot_demo.service.Orders.IOrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

    private IOrderService orderService;
    private IOrderItemService orderItemService;
    private IProductService productService;
    public OrderController(IOrderService orderService, IOrderItemService orderItemService, IProductService productService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.productService = productService;
    }
    
    @PostMapping("/order")
    public String createOrder(HttpSession session) {
        Users userFromSession = (Users) session.getAttribute("user");
        List<CartItem> listCartFromSession = (List<CartItem>) session.getAttribute("cart");
//        System.out.println(userFromSession);
//        listCartFromSession.forEach(System.out::println);
//        session.removeAttribute("cart");
        Orders orders = orderService.createOrder(userFromSession.getId());
        System.out.println(orders);
        for (CartItem item : listCartFromSession) {
            Products products = this.productService.getProductById(item.getId());
            OrderItems orderItems = new OrderItems(null, products, orders,item.getQuantity());
            OrderItems o =  this.orderItemService.createOrderItem(orderItems);
            System.out.println(o.toString());
        }
        return "redirect:/";
    }


    @GetMapping("/order/view")
    public String viewOrder(HttpSession session, Model model) {
        Users userFromSession = (Users) session.getAttribute("user");
        List<Orders> listOrders = this.orderService.getAllOrdersByUserId(userFromSession.getId());
        model.addAttribute("listOrders", listOrders);
        return "order";
    }

//    @GetMapping("/order/view/:id")
//    public String viewOrderDetail(@PathVariable("orderId") UUID orderId, HttpSession session) {
//
//
//    }
}
