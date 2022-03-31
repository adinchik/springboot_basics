package ru.geekbrains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.entities.Product;
import ru.geekbrains.repositories.ProductsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Product getProductById(int id) {
        return productsRepository.findOneById(id);
    }

    public Page<Product> getAllProducts(int page) {
        return (Page<Product>) productsRepository.findAll(new PageRequest(page, 4));
    }

    public void addProduct(Product product) {
        productsRepository.save(product);
    }

    public void deleteById(int id) {
        productsRepository.deleteById(id);
    }


}
