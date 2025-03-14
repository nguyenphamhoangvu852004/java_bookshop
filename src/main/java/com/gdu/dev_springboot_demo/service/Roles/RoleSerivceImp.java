package com.gdu.dev_springboot_demo.service.Roles;

import com.gdu.dev_springboot_demo.database.Roles.IRoleRepository;
import com.gdu.dev_springboot_demo.database.Users.IUserRepository;
import com.gdu.dev_springboot_demo.model.Roles;
import com.gdu.dev_springboot_demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleSerivceImp implements IRoleSerivce {
    private final IRoleRepository iRoleRepository;

    public RoleSerivceImp(IRoleRepository iRoleRepository) {
        this.iRoleRepository = iRoleRepository;
    }

    @Override
    public List<Roles> getAllRoles() {
        return this.iRoleRepository.getAllRoles();
    }

    @Override
    public Roles getRoleById(int id) {
        return this.iRoleRepository.getRoleById(id);
    }
}
