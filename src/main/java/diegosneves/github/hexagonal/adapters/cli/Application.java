package diegosneves.github.hexagonal.adapters.cli;

import diegosneves.github.hexagonal.adapters.cli.controller.Menu;

/**
 * A classe Application representa o ponto de entrada do programa.
 * Ela inicializa e executa o {@link Menu menu}.
 *
 * @author diegosneves
 */
public class Application {

    /**
     * O método principal é o ponto de entrada do programa.
     * Ele cria uma instância da classe {@link Menu} e chama o método {@link Menu#displayMenu() displayMenu} para iniciar o menu.
     *
     * @param args os argumentos da linha de comando
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.displayMenu();
    }

}
