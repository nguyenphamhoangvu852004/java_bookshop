package com.gdu.dev_springboot_demo.database.Users;

import com.gdu.dev_springboot_demo.model.Users;

import java.util.List;
import java.util.UUID;

public interface IUserRepository{
    Users createUser(Users users);
    Users updateUser(Users users);
    void deleteUser(UUID id);
    Users getUserById(UUID id);
    Users getUserByUsername(String username);
    List<Users> getAllUsers();

}
