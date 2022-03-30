package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.entities.ShoppingCart;
import ru.geekbrains.services.ShoppingCartService;

import javax.transaction.Transactional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/shoppingCart")
@Transactional
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(path ="/list", method = GET)
    public String showShoppingCart(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<ShoppingCart> shoppingCartItems = shoppingCartService.getAllProductsInShoppingCart(page);
        model.addAttribute("shoppingCartItems", shoppingCartItems);
        model.addAttribute("sum", shoppingCartService.totalPrice());
        model.addAttribute("currentPage", page);
        return "shopping-cart";
    }

    //@RequestMapping(path = "/add/{id}", method = RequestMethod.GET)
    @RequestMapping(value = "/add/id/{id}/page/{page}", method = GET)
    public String plusOneToShoppingCart(Model model, @PathVariable("id") int id, @RequestParam(defaultValue = "0") int page) {
        shoppingCartService.plusOneItem(id);
        model.addAttribute("shoppingCartItems", shoppingCartService.getAllProductsInShoppingCart(page));
        model.addAttribute("sum", shoppingCartService.totalPrice());
        model.addAttribute("currentPage", page);
        return "redirect:/shoppingCart/list";
    }

    @RequestMapping(value = "/remove/id/{id}/page/{page}", method = GET)
    public String deleteOneFromShoppingCart(Model model, @PathVariable("id") int id, @RequestParam(defaultValue = "0") int page) {
        shoppingCartService.remove(id);
        model.addAttribute("shoppingCartItems", shoppingCartService.getAllProductsInShoppingCart(page));
        model.addAttribute("sum", shoppingCartService.totalPrice());
        model.addAttribute("currentPage", page);
        return "redirect:/shoppingCart/list";
    }

}
