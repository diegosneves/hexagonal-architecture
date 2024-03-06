package diegosneves.github.hexagonal.adapters.cli.enums;

import java.util.Scanner;

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

    public abstract Object response(Scanner scanner, String question);

}
