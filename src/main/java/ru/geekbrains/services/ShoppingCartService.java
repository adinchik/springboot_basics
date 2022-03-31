package ru.geekbrains.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.entities.Product;
import ru.geekbrains.entities.ShoppingCart;
import ru.geekbrains.repositories.ShoppingCartRepository;

import java.util.List;

@Service
public class ShoppingCartService {
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public void setShoppingCartRepository(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public Page<ShoppingCart> getAllProductsInShoppingCart(int page) {
        return (Page<ShoppingCart>) shoppingCartRepository.findAll(new PageRequest(page, 4));
    }

    public int totalPrice() {
        int sum = 0;
        List<ShoppingCart> shoppingCartItems = (List<ShoppingCart>) shoppingCartRepository.findAll();
        for (ShoppingCart item: shoppingCartItems) {
            sum += item.getProduct_id().getPrice() * item.getCount();
        }
        return sum;
    }

    public void addProductToShoppingCart(Product product) {
        ShoppingCart item = shoppingCartRepository.getShoppingCartByProduct_id(product.getId());
        if (item != null) {
            item.setCount(item.getCount() + 1);
            shoppingCartRepository.save(item);
        } else {
            shoppingCartRepository.save(new ShoppingCart(product));
        }
//
//        List<ShoppingCart> shoppingCartItems = (List<ShoppingCart>) shoppingCartRepository.findAll();
//        for (ShoppingCart item: shoppingCartItems) {
//            if (item.getProduct_id().getId() == product.getId()) {
//                item.setCount(item.getCount() + 1);
//                shoppingCartRepository.save(item);
//                return;
//            }
//        }
//        shoppingCartRepository.save(new ShoppingCart(product));

    }

    public void plusOneItem(int id) {
        ShoppingCart item = shoppingCartRepository.findOneById(id);
        item.setCount(item.getCount() + 1);
        shoppingCartRepository.save(item);
    }

    public void remove(int id) {
        ShoppingCart item = shoppingCartRepository.findOneById(id);
        if (item.getCount() > 1) {
            item.setCount(item.getCount() - 1);
            shoppingCartRepository.save(item);
        }
        else {
            shoppingCartRepository.delete(item);
        }
    }
}
