package ru.geekbrains.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entities.Product;
import ru.geekbrains.entities.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends PagingAndSortingRepository<ShoppingCart, Integer> {
    ShoppingCart findOneById(int id);
    ShoppingCart getShoppingCartByProduct_id(int productId);
}
