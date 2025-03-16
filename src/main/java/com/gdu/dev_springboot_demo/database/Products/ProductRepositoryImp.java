package com.gdu.dev_springboot_demo.database.Products;

import com.gdu.dev_springboot_demo.model.Products;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepositoryImp implements IProductRepository {

    private EntityManager em;

    public ProductRepositoryImp(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public List<Products> findAll() {
        return this.em.createQuery("from Products", Products.class).getResultList();
    }

    @Override
    @Transactional
    public Products findById(UUID id) {
        return this.em.find(Products.class, id);
    }

    @Override
    @Transactional
    public void deleteProductById(UUID id) {
        Products product = this.em.find(Products.class, id);
        if (product != null) {
            this.em.remove(product);
        }
    }

    @Override
    @Transactional
    public void updateProduct(UUID id, Products product) {
        Products foundProduct = findById(id);
        if (foundProduct != null) {
            foundProduct.setName(product.getName());
            foundProduct.setImageUrl(product.getImageUrl());
            foundProduct.setDescription(product.getDescription());
            foundProduct.setPrice(product.getPrice());
            this.em.merge(foundProduct);
        }
    }

}
