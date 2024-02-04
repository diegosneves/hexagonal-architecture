package diegosneves.github.hexagonal.exceptions.handler;

/**
 * A classe ExceptionHandler é uma enumeração que define várias mensagens de exceções.
 * Cada mensagem corresponde a uma condição específica de validação ou erro
 * que pode ocorrer durante as operações.
 *
 * @author diegoneves
 */
public enum ExceptionHandler {

    PRICE_LESS_THAN_ZERO("O preço do Produto deve ser igual ou maior que zero"),
    PRICE_EQUAL_OR_LESS_THAN_ZERO("O preço do Produto deve ser igual ou menor que zero"),
    PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY("O ID do Produto não deve ser vazio ou nulo"),
    PRODUCT_NAME_SHOULD_NOT_BE_NULL_OR_EMPTY("O Nome do Produto não deve ser vazio ou nulo"),
    INVALID_UUID_FORMAT_MESSAGE("O ID do Produto [%s] precisa ser no formato UUID"),
    PRODUCT_STATUS_SHOULD_NOT_BE_NULL("O Status do Produto não deve ser nulo");

    private final String message;

    ExceptionHandler(String message) {
        this.message = message;

    }

    /**
     * Retorna a mensagem associada à exceção.
     *
     * @return A mensagem associada à exceção.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Formata uma mensagem com a entrada fornecida e retorna a mensagem formatada.
     *
     * @param message A mensagem de entrada que será formatada.
     * @return A mensagem após a formatação.
     */
    public String getMessage(String message) {
        return String.format(this.message, message);
    }

}
