package com.gdu.dev_springboot_demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.dev_springboot_demo.model.CartItem;
import com.gdu.dev_springboot_demo.model.Users;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    public List<CartItem> getCart(HttpSession session) {
        List<CartItem> listCartItems = (List<CartItem>) session.getAttribute("cart");
        if (listCartItems == null) {
            listCartItems = new ArrayList<>();
            session.setAttribute("cart", listCartItems);
        }
        return listCartItems;
    }

    @PostMapping("/add")
    @ResponseBody
    public String addToCart(@RequestParam UUID id, @RequestParam String name,
            @RequestParam double price, @RequestParam String imageUrl,
            @RequestParam int quantity, HttpSession session) {

        List<CartItem> cart = getCart(session);
        Users user = (Users) session.getAttribute("user");
        if ( user == null) {
            return "Vui lòng đăng nhập trên trang chủ";
        }
        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        for (CartItem item : cart) {
            if (item.getId().equals(id)) {
                item.setQuantity(item.getQuantity() + quantity);
                return "Sản phẩm đã được cập nhật số lượng!";
            }
        }

        // Nếu chưa có, thêm sản phẩm mới
        cart.add(new CartItem(id, name, price, imageUrl, quantity));

        return "Sản phẩm đã thêm vào giỏ hàng!";
    }

    @GetMapping("/view")
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cart = getCart(session);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/update")
    public String updateCart(@RequestParam UUID id, @RequestParam int quantity, HttpSession session) {
        List<CartItem> cart = getCart(session);
        cart.removeIf(item -> item.getId().equals(id) && quantity <= 0);
        for (CartItem item : cart) {
            if (item.getId().equals(id)) {
                item.setQuantity(quantity);
                break;
            }
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart/view";
    }

}
