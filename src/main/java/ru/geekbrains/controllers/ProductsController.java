package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.entities.Product;
import ru.geekbrains.services.ProductsService;
import ru.geekbrains.services.ShoppingCartService;

import javax.transaction.Transactional;
import java.util.List;


@Controller
@RequestMapping("/products")
@Transactional
public class ProductsController {
    private ProductsService productsService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(path = "/{sid}", method = RequestMethod.GET)
    public String showProduct(Model model, @PathVariable("sid") int id) {
        model.addAttribute("product", productsService.getProductById(id));
        return "show-product";
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String showAllProducts(Model model, @RequestParam(defaultValue = "0") int page) {
        //int page = Integer.getInteger(pageS);
        Page<Product> products = productsService.getAllProducts(page);
        model.addAttribute("products", products);
        System.out.println(page);
        System.out.println(products.getTotalPages());
        model.addAttribute("currentPage", page);
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
    public String deleteProduct(Model model, @PathVariable("sid") int id, @RequestParam(defaultValue = "0") int page) {
        productsService.deleteById(id);
        model.addAttribute("products", productsService.getAllProducts(page));
        return "redirect:/products/list";
    }

    @RequestMapping(path="/addToShoppingCart/{id}", method = RequestMethod.GET)
    public String addProductToShoppingCart(Model model, @PathVariable("id") int id, @RequestParam(defaultValue = "0") int page, RedirectAttributes redirAttrs) {
        Product product = productsService.getProductById(id);
        shoppingCartService.addProductToShoppingCart(product);
        redirAttrs.addFlashAttribute("success", "Product " + product.getTitle() + " was added to Shopping cart successfully");
        model.addAttribute("products", productsService.getAllProducts(page));
        return "redirect:/products/list";
    }

}
