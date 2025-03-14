package com.gdu.dev_springboot_demo.service.Users;

import com.gdu.dev_springboot_demo.model.Users;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    void createUser(Users users);
    void updateUser(Users users);
    void deleteUser(Users users);
    Users getUserById(UUID id);
    Users getUserByUsername(String username);
    List<Users> getAllUsers();

    Users login(Users users);
}
