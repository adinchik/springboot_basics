package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Product> getAllProducts() {
        return (List<Product>)productsRepository.findAll();
    }

    public void addProduct(Product product) {
        productsRepository.save(product);
    }

    public void deleteById(int id) {
        productsRepository.deleteById(id);
    }
}
