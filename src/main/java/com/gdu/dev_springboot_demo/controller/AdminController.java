package com.gdu.dev_springboot_demo.controller;

import com.gdu.dev_springboot_demo.database.Orders.IOrderRepository;
import com.gdu.dev_springboot_demo.model.Orders;
import com.gdu.dev_springboot_demo.model.Products;
import com.gdu.dev_springboot_demo.service.Orders.IOrderService;
import com.gdu.dev_springboot_demo.service.Products.IProductService;
import com.gdu.dev_springboot_demo.service.Users.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class AdminController {

    private final IUserService iUserService;
    private final IProductService iProductService;
    private final IOrderService iOrderService;

    public AdminController(
            IUserService iUserService,
            IProductService iProductService,
            IOrderService iOrderService
    ) {
        this.iUserService = iUserService;
        this.iProductService = iProductService;
        this.iOrderService = iOrderService;
    }

    @GetMapping("/admin/users")
    public String getUsers(Model model) {
        model.addAttribute("users", iUserService.getAllUsers());
        return "admin/admin-users";
    }

    @PostMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable UUID id) {
        iUserService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/products")
    public String managementProductsPage(Model model){
        model.addAttribute("products", iProductService.getAllProducts());
        return "admin/admin-products";
    }

    @PostMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable UUID id) {
        iProductService.deleteProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Products product = iProductService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/edit-product";
    }

    @PostMapping("/admin/products/update/{id}")
    public String updateProduct(@PathVariable UUID id, @ModelAttribute Products updatedProduct) {
        iProductService.updateProduct(id, updatedProduct);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/orders")
    public String managementOrdersPage(@RequestParam(required = false) String status, Model model){
        List<Orders> orders;
        if(status == null || status.isEmpty()){
            orders = this.iOrderService.getAllOrders();
        }else{
            orders = this.iOrderService.filterOrdersByStatus(status);
        }
        model.addAttribute("orders", orders);
        model.addAttribute("selectedStatus", status);
        return "admin/admin-orders";
    }

    @PostMapping("/admin/order/{orderId}/{status}")
    public String updateOrderStatus(@PathVariable String status, @PathVariable UUID orderId) {
        this.iOrderService.updateOrder(orderId, status);
        return "redirect:/admin/orders";
    }

}
