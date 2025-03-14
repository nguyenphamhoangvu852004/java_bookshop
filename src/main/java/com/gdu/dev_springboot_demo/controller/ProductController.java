package com.gdu.dev_springboot_demo.controller;

import com.gdu.dev_springboot_demo.model.Products;
import com.gdu.dev_springboot_demo.service.Products.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class ProductController {
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    private IProductService iProductService;

    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @GetMapping("/detailProduct/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        Products product = iProductService.getProductById(id);
        System.out.println(product.toString());
        model.addAttribute("product", product);
        return "detailProduct";
    }
}
