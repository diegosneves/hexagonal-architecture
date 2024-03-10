package diegosneves.github.hexagonal.adapters.rest.dto;

/**
 * A classe {@link ExceptionDTO} representa um objeto de transferência de dados
 * para transportar informações de exceção.
 * Ela contém a mensagem e o código de status da exceção.
 *
 * @author diegosneves
 */
public record ExceptionDTO(String message, int statusCode) {
}
