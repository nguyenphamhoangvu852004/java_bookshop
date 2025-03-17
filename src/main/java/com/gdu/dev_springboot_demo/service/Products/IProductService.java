package com.gdu.dev_springboot_demo.service.Products;

import com.gdu.dev_springboot_demo.model.Products;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    List<Products> getAllProducts();

    Products getProductById(UUID id);

    void deleteProductById(UUID id);

    void updateProduct(UUID id, Products product);

}
