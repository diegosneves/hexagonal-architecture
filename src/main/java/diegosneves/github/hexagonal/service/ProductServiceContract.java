package diegosneves.github.hexagonal.service;

import diegosneves.github.hexagonal.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.exceptions.ProductException;

/**
 * A interface {@link ProductServiceContract} fornece métodos para recuperação, criação, ativação e desativação de {@link ProductContract produtos}.
 *
 * @author diegoneves
 */
public interface ProductServiceContract {

    /**
     * Recupera um {@link ProductContract produto} com o {@link java.util.UUID ID} fornecido.
     *
     * @param id O ID do produto a ser recuperado.
     * @return O produto recuperado como objeto ProductContract.
     * @throws ProductException Se o ID for nulo ou vazio.
     */
    ProductContract get(String id) throws ProductException;

    /**
     * Método para criar um novo {@link ProductContract produto}
     *
     * @param productName  O nome do produto a ser criado
     * @param productPrice O preço do produto a ser criado
     * @return Retorna o produto criado
     */
    ProductContract create(String productName, Double productPrice);

    /**
     * Método para habilitar um {@link ProductContract produto}
     *
     * @param product O produto a ser habilitado
     * @return Retorna o produto habilitado
     */
    ProductContract enable(ProductContract product);

    /**
     * Método para desabilitar um {@link ProductContract produto}
     *
     * @param product O produto a ser desabilitado
     * @return Retorna o produto desabilitado
     */
    ProductContract disable(ProductContract product);


}
