package ru.geekbrains;

import ru.geekbrains.entities.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "category_id")
    private List<Product> products;

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        String s = "Category [ " + id + " " + title + " ";
        for (Product product : products) {
            s += product.getTitle() + " ";
        }
        s += "]";
        return s;
    }
}