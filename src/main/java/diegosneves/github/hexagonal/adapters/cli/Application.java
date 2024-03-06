package diegosneves.github.hexagonal.adapters.cli;

import diegosneves.github.hexagonal.adapters.cli.controller.Menu;

public class Application {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.displayMenu();
    }

}
