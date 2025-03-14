package com.gdu.dev_springboot_demo.service.Roles;

import com.gdu.dev_springboot_demo.model.Roles;
import com.gdu.dev_springboot_demo.model.Users;

import javax.management.relation.Role;
import java.util.List;
import java.util.UUID;

public interface IRoleSerivce {
    List<Roles> getAllRoles();
    Roles getRoleById(int id);
}
