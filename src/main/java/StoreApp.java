import entity.User;

import menu.Menu;
import repository.ProductRepository;
import repository.UserRepository;

import util.ApplicationContext;

import java.util.Optional;
import java.util.Scanner;

public class StoreApp {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.displayMainMenu();
    }
}

