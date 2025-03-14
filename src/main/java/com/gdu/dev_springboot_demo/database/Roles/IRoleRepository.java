package com.gdu.dev_springboot_demo.database.Roles;

import com.gdu.dev_springboot_demo.model.Roles;
import com.gdu.dev_springboot_demo.model.Users;

import java.util.List;
import java.util.UUID;

public interface IRoleRepository {
    List<Roles> getAllRoles();
    Roles getRoleById(int id);
    void deleteRoleById(int id);
    Roles createRole(Roles roles);
    Roles updateRole(Roles roles);

}
