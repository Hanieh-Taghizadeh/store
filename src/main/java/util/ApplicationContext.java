package util;

import entity.User;
import repository.ProductRepository;
import repository.UserRepository;
import service.ProductService;
import service.UserService;

public class ApplicationContext {
    private static ApplicationContext instance;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final UserService userService;
    private User currentUser;

    private ApplicationContext() {
        userRepository = new UserRepository();
        productRepository = new ProductRepository();
        productService = new ProductService();
        userService = new UserService(userRepository);
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public ProductService getProductService() {
        return productService;
    }

    public UserService getUserService() {
        return userService;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}