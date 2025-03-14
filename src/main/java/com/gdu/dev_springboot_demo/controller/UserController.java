package com.gdu.dev_springboot_demo.controller;

import com.gdu.dev_springboot_demo.model.Products;
import com.gdu.dev_springboot_demo.model.Users;
import com.gdu.dev_springboot_demo.service.Products.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class UserController {
    private IProductService iProductService;

    public UserController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @GetMapping("/")
    public String dashboard(@ModelAttribute("user") Users user, Model model) {
        List<Products> listProducts = iProductService.getAllProducts();
        model.addAttribute("user", user);
        model.addAttribute("listProducts", listProducts);
        return "home";
    }

}
