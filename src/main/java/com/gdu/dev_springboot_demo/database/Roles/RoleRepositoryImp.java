package com.gdu.dev_springboot_demo.database.Roles;


import com.gdu.dev_springboot_demo.model.Roles;
import com.gdu.dev_springboot_demo.model.Users;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RoleRepositoryImp implements IRoleRepository {
    private EntityManager em;

    public RoleRepositoryImp(EntityManager em) {
        this.em = em;
    }


    @Override
    @Transactional
    public List<Roles> getAllRoles() {
        return this.em.createQuery("from Roles", Roles.class).getResultList();
    }

    @Override
    @Transactional
    public Roles getRoleById(int id) {
        return this.em.find(Roles.class, id);
    }

    @Override
    @Transactional
    public void deleteRoleById(int id) {
            this.em.remove(this.em.find(Roles.class, id));
    }

    @Override
    @Transactional
    public Roles createRole(Roles roles) {
        return this.em.merge(roles);
    }

    @Override
    @Transactional
    public Roles updateRole(Roles roles) {
        return this.em.merge(roles);
    }
}
