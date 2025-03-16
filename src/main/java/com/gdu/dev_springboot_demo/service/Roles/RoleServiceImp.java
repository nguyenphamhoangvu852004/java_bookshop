package com.gdu.dev_springboot_demo.service.Roles;

import com.gdu.dev_springboot_demo.database.Roles.IRoleRepository;
import com.gdu.dev_springboot_demo.model.Roles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements IRoleSerivce {
    private final IRoleRepository iRoleRepository;

    public RoleServiceImp(IRoleRepository iRoleRepository) {
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
