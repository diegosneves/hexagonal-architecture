package diegosneves.github.hexagonal.adapters.cli.controller;

import diegosneves.github.hexagonal.adapters.cli.dto.ProductEntityDTO;
import diegosneves.github.hexagonal.adapters.cli.enums.QuestionHandler;
import diegosneves.github.hexagonal.adapters.cli.service.ProductEntityService;
import diegosneves.github.hexagonal.adapters.cli.service.impl.ProductServiceImpl;

import java.util.Scanner;

/**
 * A classe Menu representa um menu com opções e funcionalidades para exibir e interagir com o menu.
 * Cada opção do menu corresponde a uma funcionalidade diferente.
 * <pre>{@code
 * As opções disponíveis são:
 * 1 - Obter um produto pelo ID
 * 2 - Criar um novo produto
 * 3 - Ativar um produto pelo ID
 * 4 - Desativar um produto pelo ID
 * 5 - Sair
 * }</pre>
 * <p>
 * O usuário pode interagir com o menu inserindo o número correspondente à opção desejada.
 * Se a opção escolhida for válida, será executada a funcionalidade correspondente.
 * Caso contrário, será exibida uma mensagem de erro ("Opção Inválida!!")
 * e o menu será apresentado novamente para que o usuário possa tentar novamente..
 * <p>
 * <b>Vale ressaltar que esta é apenas uma classe de exemplo para fins didáticos e, portanto, não segue necessariamente as melhores práticas de desenvolvimento de software.</b>
 *
 * @author diegoneves
 */
public class Menu {

    private static final String MENU_MESSAGE = "Escolha uma opção%s:";
    private static final String SINGLE_OPTION = " [Existe apenas a opção 1]";
    private static final String TOTAL_OPTIONS = " [Entre 1 - %d]";
    private static final String NEW_LINE = "\n";
    private static final String MENU_DIVIDER = "=";
    private static final String MENU_OPTION_SEPARATOR = " - ";
    private static final int MENU_DIVIDER_SIZE = 50;

    private final ProductEntityService productEntityService;

    public Menu() {
        this.productEntityService = new ProductServiceImpl();
    }

    public void displayMenu() {
        boolean isOn = Boolean.TRUE;
        Scanner scanner = new Scanner(System.in);

        while (isOn) {
            int option = this.optionBuilder(scanner,
                    "Obter Produto por ID",
                    "Criar um produto",
                    "Ativar Produto por ID",
                    "Desativar Produto por ID",
                    "Sair");
            switch (option) {
                case 1:
                    System.out.println("Obter Produto por ID");
                    String id = (String) QuestionHandler.STRING.response(scanner, "Informe o ID desejado");
                    ProductEntityDTO product = this.productEntityService.get(id);
                    System.out.println(product);
                    break;
                case 2:
                    System.out.println("Criar um produto");
                    String productName = (String) QuestionHandler.STRING.response(scanner, "Informe o nome do Produto");
                    Double productPrice = (Double) QuestionHandler.DOUBLE.response(scanner, "Informe o preço do Produto");
                    ProductEntityDTO createdProduct = this.productEntityService.create(productName, productPrice);
                    System.out.println(createdProduct);
                    break;
                case 3:
                    System.out.println("Ativar Produto por ID");
                    String ativarId = (String) QuestionHandler.STRING.response(scanner, "Informe o ID do Produto para ativação");
                    ProductEntityDTO enabledProduct = this.productEntityService.enable(ativarId);
                    System.out.println(enabledProduct);
                    break;
                case 4:
                    System.out.println("Desativar Produto por ID");
                    String desativaId = (String) QuestionHandler.STRING.response(scanner, "Informe o ID do Produto para desativação");
                    ProductEntityDTO disabledProduct = this.productEntityService.disable(desativaId);
                    System.out.println(disabledProduct);
                    break;
                case 5:
                    isOn = Boolean.FALSE;
                    break;
                default:
                    System.out.println("Opção Inválida!!");
                    break;
            }

        }
        scanner.close();
    }

    private int optionBuilder(Scanner scanner, String... options) {
        int option = 0;
        StringBuilder sb = new StringBuilder(MENU_DIVIDER.repeat(MENU_DIVIDER_SIZE)).append(NEW_LINE);
        for (int i = 0; i < options.length; i++) {
            if (i == options.length - 1) {
                sb.append(i + 1).append(MENU_OPTION_SEPARATOR).append(options[i]).append(NEW_LINE).append(MENU_DIVIDER.repeat(MENU_DIVIDER_SIZE));
            } else {
                sb.append(i + 1).append(MENU_OPTION_SEPARATOR).append(options[i]).append(NEW_LINE);
            }
        }
        System.out.println(sb);
        System.out.printf(MENU_MESSAGE, options.length == 1 ? SINGLE_OPTION : String.format(TOTAL_OPTIONS, options.length));
        try {
            option = scanner.nextInt();
        } catch (Exception ignored) {
            return 0;
        }
        return option;
    }

}
