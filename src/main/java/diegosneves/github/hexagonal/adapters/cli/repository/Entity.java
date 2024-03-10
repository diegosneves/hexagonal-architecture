package diegosneves.github.hexagonal.adapters.cli.repository;

import java.io.Serializable;

/**
 * Representa uma entidade que pode ser serializada e desserializada.
 *
 * @param <T> o tipo da entidade.
 *
 * @author diegosneves
 */
public interface Entity <T> extends Serializable {

    /**
     * Serializa a entidade em uma representação de string.
     * Este método converte a entidade em uma string usando um formato específico.
     *
     * @return Uma representação de string da entidade serializada.
     */
    String serialize();

    /**
     * Desserializa dados de entrada e cria uma instância do tipo T.
     *
     * @param inputDateForDeserialization Os dados de entrada usados para a desserialização.
     * @return A instância desserializada do tipo T.
     */
    T deserialize(String... inputDateForDeserialization);

}
