package repository;

import entity.Product;
import enums.Electronic;
import enums.Shoe;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public ProductRepository() {
        for (Electronic e : Electronic.values()) {
            products.add(new Product(e.getName(), e.getPrice(), 10)); // initial stock
        }
        for (Shoe s : Shoe.values()) {
            products.add(new Product(s.getName(), s.getPrice(), 10)); // initial stock
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product findByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
