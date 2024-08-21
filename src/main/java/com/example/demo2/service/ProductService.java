package com.example.demo2.service;

import com.example.demo2.model.Product;
import com.example.demo2.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }
    public Product getProduct(int id) {
        return repo.findById(id).orElse(null);
    }
}
