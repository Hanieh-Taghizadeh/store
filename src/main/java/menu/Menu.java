package menu;

import entity.User;

import repository.ProductRepository;
import repository.UserRepository;
import service.UserService;
import util.ApplicationContext;

import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private final ApplicationContext context;

    public Menu() {
        context = ApplicationContext.getInstance();
    }

    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        UserService userService = context.getUserService();

        System.out.println("Welcome to Online Store");
        System.out.print("Enter 1 for Sign Up, 2 for Login: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                User user = userService.signUp(username, password);
                context.setCurrentUser(user);
                System.out.println("Sign Up successful!");

            } else if (choice == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                User user = userService.login(username, password);
                context.setCurrentUser(user);
                System.out.println("Login successful!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. View products");
            System.out.println("2. Add product to cart");
            System.out.println("3. Remove product from cart");
            System.out.println("4. View cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int menuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (menuChoice) {
                case 1 -> viewProducts();
                case 2 -> addProductToCart(scanner);
                case 3 -> removeProductFromCart(scanner);
                case 4 -> viewCart();
                case 5 -> checkout();
                case 6 -> exit = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewProducts() {
        context.getProductRepository().getProducts().forEach(product ->
                System.out.println(product.getName() + " - $" + product.getPrice() + " - Available: " + product.getQuantity()));
    }

    private void addProductToCart(Scanner scanner) {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            context.getProductService().addProductToCart(productName, context.getCurrentUser(), quantity);
            System.out.println("Product added to cart successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeProductFromCart(Scanner scanner) {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            context.getProductService().removeProductFromCart(productName, context.getCurrentUser(), quantity);
            System.out.println("Product removed from cart successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewCart() {
        context.getProductService().printCart(context.getCurrentUser());
    }

    private void checkout() {
        double totalPrice = context.getProductService().getTotalPrice(context.getCurrentUser());
        System.out.println("Total Price: $" + totalPrice);
        context.getProductService().checkout(context.getCurrentUser());
        System.out.println("Checkout successful!");
    }
}
