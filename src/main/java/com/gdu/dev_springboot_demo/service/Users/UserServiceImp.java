package com.gdu.dev_springboot_demo.service.Users;

import com.gdu.dev_springboot_demo.database.Users.IUserRepository;
import com.gdu.dev_springboot_demo.model.Users;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements IUserService {
    private final IUserRepository iUserRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    public UserServiceImp(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public void createUser(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        this.iUserRepository.createUser(users);
    }

    @Override
    public void updateUser(Users users) {
        this.iUserRepository.updateUser(users);

    }

    @Override
    public void deleteUser(UUID id) {
        this.iUserRepository.deleteUser(id);
    }

    @Override
    public Users getUserById(UUID id) {
        return this.iUserRepository.getUserById(id);
    }

    @Override
    public Users getUserByUsername(String username) {
        return this.iUserRepository.getUserByUsername(username);
    }

    @Override
    public List<Users> getAllUsers() {
        return this.iUserRepository.getAllUsers();
    }

    @Override
    public Users login(Users users) {
        Users userFromDB = this.iUserRepository.getUserByUsername(users.getUsername());
        if (userFromDB != null) {
            if (passwordEncoder.matches(users.getPassword(), userFromDB.getPassword())) {
                return userFromDB;
            }
        }
        return null;
    }
}
