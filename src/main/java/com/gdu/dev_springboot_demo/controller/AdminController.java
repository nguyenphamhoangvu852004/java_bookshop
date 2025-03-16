package com.gdu.dev_springboot_demo.controller;

import com.gdu.dev_springboot_demo.model.Products;
import com.gdu.dev_springboot_demo.service.Products.IProductService;
import com.gdu.dev_springboot_demo.service.Users.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class AdminController {

    private final IUserService iUserService;
    private final IProductService iProductService;

    public AdminController(IUserService iUserService, IProductService iProductService) {
        this.iUserService = iUserService;
        this.iProductService = iProductService;
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

    @GetMapping("/admin/orders")
    public String managementOrdersPage(){
        return "admin/admin-orders";
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
}
