package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entities.Product;
import ru.geekbrains.services.ProductsService;

import javax.transaction.Transactional;
import java.util.List;


@Controller
@RequestMapping("/products")
@Transactional
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @RequestMapping(path = "/{sid}", method = RequestMethod.GET)
    public String showProduct(Model model, @PathVariable("sid") int id) {
        model.addAttribute("product", productsService.getProductById(id));
        return "show-product";
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String showAllProducts(Model model) {
        List<Product> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "products-list";
    }

    @RequestMapping(path="/add", method=RequestMethod.GET)
    public String showAddForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add-product";
    }

    @RequestMapping(path="/add", method=RequestMethod.POST)
    public String showAddForm(Product product) {
        System.out.println(product.getTitle());
        productsService.addProduct(product);
        return "redirect:/products/list";
    }

    @RequestMapping(path = "/remove/{sid}", method = RequestMethod.GET)
    public String deleteProduct(Model model, @PathVariable("sid") int id) {
        productsService.deleteById(id);
        model.addAttribute("products", productsService.getAllProducts());
        return "redirect:/products/list";
    }

}
