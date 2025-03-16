package com.gdu.dev_springboot_demo.database.Products;

import com.gdu.dev_springboot_demo.model.Products;

import java.util.List;
import java.util.UUID;

public interface IProductRepository {

    List<Products> findAll();
    Products findById(UUID id);
    void deleteProductById(UUID id);
    void updateProduct(UUID id, Products product);
}
