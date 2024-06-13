package service;

import entity.Product;
import entity.User;
import repository.ProductRepository;

public class ProductService {  private final ProductRepository productRepository = new ProductRepository();

    public void addProductToCart(String productName, User user, int quantity) throws Exception {
        Product product = productRepository.findByName(productName);
        if (product == null) {
            throw new Exception("Product not found");
        }
        if (product.getQuantity() < quantity) {
            throw new Exception("Not enough stock");
        }

        product.setQuantity(product.getQuantity() - quantity);
        user.getCart().put(product, user.getCart().getOrDefault(product, 0) + quantity);
    }

    public void removeProductFromCart(String productName, User user, int quantity) throws Exception {
        Product product = user.getCart().keySet().stream()
                .filter(p -> p.getName().equals(productName))
                .findFirst()
                .orElse(null);

        if (product == null) {
            throw new Exception("Product not found in cart");
        }

        int currentQuantity = user.getCart().get(product);
        if (currentQuantity < quantity) {
            throw new Exception("Not enough quantity in cart");
        }

        if (currentQuantity == quantity) {
            user.getCart().remove(product);
        } else {
            user.getCart().put(product, currentQuantity - quantity);
        }

        product.setQuantity(product.getQuantity() + quantity);
    }

    public void printCart(User user) {
        user.getCart().forEach((product, quantity) -> {
            System.out.println(product.getName() + " - " + quantity + " - $" + product.getPrice() * quantity);
        });
    }

    public double getTotalPrice(User user) {
        return user.getCart().entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public void checkout(User user) {
        user.getCart().clear();
    }
}
