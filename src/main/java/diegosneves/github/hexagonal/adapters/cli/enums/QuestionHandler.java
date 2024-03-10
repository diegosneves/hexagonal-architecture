package diegosneves.github.hexagonal.adapters.cli.enums;

import java.util.Scanner;

/**
 * O enum {@link QuestionHandler} fornece diferentes tipos de perguntas e seus respectivos métodos de resposta.
 * Cada constante do enum representa um tipo diferente de resposta: STRING, DOUBLE e INTEGER.
 * O método de resposta recebe um objeto Scanner e uma pergunta como entrada e retorna a resposta digitada.
 *
 * @author diegosneves
 */
public enum QuestionHandler{

    STRING {
        @Override
        public Object response(Scanner scanner, String question) {
            String value = "";
            System.out.printf(QUESTION_FORMAT, question);
            value = scanner.next();
            return value;
        }
    },
    DOUBLE {
        @Override
        public Object response(Scanner scanner, String question) {
            scanner.nextLine();
            Double value = 0.0;
            System.out.printf(QUESTION_FORMAT, question);
            try {
                value = scanner.nextDouble();
            } catch (Exception ignored) {
                System.out.println("Double invalido");
                value = (Double) this.response(scanner, question);
            }
            return value;
        }
    },
    INTEGER {
        @Override
        public Object response(Scanner scanner, String question) {
            scanner.nextLine();
            Integer value = 0;
            System.out.printf(QUESTION_FORMAT, question);
            try {
                value = scanner.nextInt();
            } catch (Exception ignored) {
                System.out.println("Integer invalido");
                value = (Integer) this.response(scanner,question);
            }
            return value;
        }
    };

    private static final String QUESTION_FORMAT = "-> %s?\n- ";

    /**
     * Este método fornece uma resposta para uma determinada pergunta com base no tipo de entrada.
     *
     * @param scanner O objeto {@link Scanner} usado para ler a entrada do usuário.
     * @param question A pergunta a ser exibida ao usuário.
     * @return A resposta para a pergunta.
     */
    public abstract Object response(Scanner scanner, String question);

}
