package diegosneves.github.hexagonal.repository;

import diegosneves.github.hexagonal.domain.product.entity.ProductContract;

/**
 * A interface {@link ProductReader} oferece métodos para obter informações sobre o {@link ProductContract produto}.
 *
 * @author diegoneves
 */
public interface ProductReader {

    /**
     * Retorna o contrato de {@link ProductContract produto} associado ao {@link java.util.UUID ID} único fornecido.
     *
     * @param id O ID único do produto.
     * @return O contrato do produto correspondente ao ID.
     */
    ProductContract get(String id);

}
