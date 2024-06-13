package service;

import entity.User;
import repository.UserRepository;

public class UserService {  private final UserRepository userRepository = new UserRepository();

    public User signUp(String username, String password) throws Exception {
        if (userRepository.findByUsername(username) != null) {
            throw new Exception("Username already exists");
        }
        User user = new User(username, password);
        userRepository.saveUser(user);
        return user;
    }

    public User login(String username, String password) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new Exception("Invalid username or password");
        }
        return user;
    }
}
