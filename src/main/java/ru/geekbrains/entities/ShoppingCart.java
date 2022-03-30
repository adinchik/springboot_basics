package ru.geekbrains.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product_id;

    @Column(name = "count")
    private int count;

    public ShoppingCart() {
    }

    public ShoppingCart(Product product_id) {
        this.product_id = product_id;
        this.count = 1;
    }

    public int getPrice() {
        return product_id.getPrice() * count;
    }

}
