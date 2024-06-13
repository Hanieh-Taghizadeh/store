package entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class User {
    private String username;
    private String password;
    private Map<Product, Integer> cart = new HashMap<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
