package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/**")
@RestController
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") int id) {
        return productsService.getProductById(id);
    }

    @GetMapping("/products")
    public List<Product> showAllProducts() {
        return productsService.getAllProducts();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productsService.saveOrUpdateProduct(product);
    }

    @PutMapping(path = "/products", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Product updateProduct(@RequestBody Product product) {
        return productsService.saveOrUpdateProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public int deleteProduct(@PathVariable("id") int id) {
        productsService.deleteById(id);
        return HttpStatus.OK.value();
    }

}
