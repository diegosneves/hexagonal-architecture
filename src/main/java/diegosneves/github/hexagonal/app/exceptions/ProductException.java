package diegosneves.github.hexagonal.app.exceptions;

import diegosneves.github.hexagonal.app.exceptions.handler.ExceptionHandler;

/**
 * A classe ProductException representa uma exceção que pode ser lançada durante as operações com produtos.
 * Esta classe estende a classe {@link RuntimeException}.
 *
 * @see RuntimeException
 * @author diegoneves
 */
public class ProductException extends RuntimeException {


    /**
     * Construtor que recebe um {@link ExceptionHandler handler} de exceções.
     *
     * @param handler O handler para tratar a exceção.
     */
    public ProductException(ExceptionHandler handler) {
        super(handler.getMessage());
    }

    /**
     * Construtor que recebe um {@link ExceptionHandler handler} de exceções e uma mensagem.
     *
     * @param handler O handler para tratar a exceção.
     * @param message A mensagem para ser usada na exceção.
     */
    public ProductException(ExceptionHandler handler, String message) {
        super(handler.getMessage(message));
    }
}
