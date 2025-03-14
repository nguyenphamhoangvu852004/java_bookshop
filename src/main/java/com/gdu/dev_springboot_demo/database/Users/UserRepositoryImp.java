package com.gdu.dev_springboot_demo.database.Users;


import com.gdu.dev_springboot_demo.model.Roles;
import com.gdu.dev_springboot_demo.model.Users;
import com.gdu.dev_springboot_demo.service.Roles.IRoleSerivce;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepositoryImp implements IUserRepository {
    private EntityManager em;
    private IRoleSerivce iRoleSerivce;

    public UserRepositoryImp(EntityManager em, IRoleSerivce iRoleSerivce) {
        this.em = em;
        this.iRoleSerivce = iRoleSerivce;
    }

    @Override
    @Transactional
    public Users createUser(Users users) {
        if (users.getRoles() == null){
            Roles defaultRole = this.iRoleSerivce.getRoleById(2);
            users.setRoles(defaultRole);
        }
        this.em.persist(users);
        this.em.flush();
        return users;
    }

    @Override
    public Users updateUser(Users users) {
        return this.em.merge(users);
    }

    @Override
    public void deleteUser(Users users) {
        this.em.remove(users);
    }

    @Override
    public Users getUserById(UUID id) {
        return this.em.find(Users.class, id);
    }

   @Override
   @Transactional
public Users getUserByUsername(String username) {
    try {
        return em.createQuery("SELECT u FROM Users u WHERE u.username = :username", Users.class)
                 .setParameter("username", username)
                 .getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}

    @Override
    public List<Users> getAllUsers() {
        return this.em.createQuery("SELECT u FROM Users u", Users.class).getResultList();
    }
}
