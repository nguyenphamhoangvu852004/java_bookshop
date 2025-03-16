package com.gdu.dev_springboot_demo.controller;

import com.gdu.dev_springboot_demo.model.Users;
import com.gdu.dev_springboot_demo.service.Roles.IRoleSerivce;
import com.gdu.dev_springboot_demo.service.Users.IUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private IUserService userService;
    private IRoleSerivce roleService;

    public AuthController(IUserService userService, IRoleSerivce roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/formLogin")
    public String formLogin(Model model) {
        model.addAttribute("user", new Users());
        return "/form/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") @Valid Users user, BindingResult bindingResult, HttpSession session,
            Model model) {

        Users checkUser = this.userService.getUserByUsername(user.getUsername());
        System.out.println(checkUser);
        // Nếu tài khoản không tồn tại, báo lỗi ngay và quay lại form login
        if (checkUser == null) {
            bindingResult.rejectValue("username", "error.username", "Tài khoản không tồn tại");
            model.addAttribute("user", user); // Truyền lại user vào model
            return "form/login";
        }

        // Kiểm tra mật khẩu
        Users userAfterLogin = this.userService.login(user);
        if (userAfterLogin == null) {
            bindingResult.rejectValue("password", "error.password", "Sai mật khẩu");
            model.addAttribute("user", user); // Truyền lại user vào model
            return "form/login";
        }

        if (bindingResult.hasErrors()) {
            return "form/login";
        }

        // Check role
        if(checkUser.getRoles().equals(this.roleService.getRoleById(1))){
            session.setAttribute("user", userAfterLogin);
            return "admin/admin-users";
        }

        // Nếu đăng nhập thành công, lưu vào session
        session.setAttribute("user", userAfterLogin);
        return "redirect:/"; // Chuyển hướng về trang chủ hoặc dashboard
    }

    @GetMapping("/formRegister")
    public String formRegister(Model model) {
        model.addAttribute("user", new Users());
        return "form/register";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute("user") @Valid Users user,
            BindingResult bindingResult,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {
        Users checkUser = this.userService.getUserByUsername(user.getUsername());

        if (checkUser != null &&checkUser.getUsername().equals(user.getUsername())) {
            bindingResult.rejectValue("username", "error.username", "Tài khoản đã tạo");
            return "form/register";
        }

        if (!user.getPassword().equals(confirmPassword)) {
            bindingResult.rejectValue("password", "error.password", "Mật khẩu không khớp");
            return "form/register";
        }
        if (bindingResult.hasErrors()) {
            return "form/register";
        }

        this.userService.createUser(user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
