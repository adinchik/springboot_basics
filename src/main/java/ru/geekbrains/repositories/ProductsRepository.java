package ru.geekbrains.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entities.Product;

@Repository
public interface ProductsRepository extends PagingAndSortingRepository<Product, Integer> {
    Product findOneById(int id);
}
