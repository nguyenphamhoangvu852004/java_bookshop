package com.gdu.dev_springboot_demo.service.Products;


import com.gdu.dev_springboot_demo.database.Products.IProductRepository;
import com.gdu.dev_springboot_demo.model.Products;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImp implements IProductService {
    private final IProductRepository iProductRepository;

    public ProductServiceImp(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    @Override
    public List<Products> getAllProducts() {
        return iProductRepository.findAll();
    }

    @Override
    public Products getProductById(UUID id) {
        return iProductRepository.findById(id);
    }

    @Override
    public void deleteProductById(UUID id) {
        iProductRepository.deleteProductById(id);
    }

    @Override
    public void updateProduct(UUID id, Products product) {
        iProductRepository.updateProduct(id, product);
    }
}
