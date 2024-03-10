package diegosneves.github.hexagonal.adapters.cli.service;

import diegosneves.github.hexagonal.adapters.cli.dto.ProductEntityDTO;
import diegosneves.github.hexagonal.app.exceptions.ProductException;

/**
 * A interface {@link  ProductEntityService} fornece métodos para buscar, criar,
 * habilitar e desabilitar entidades de produtos.
 *
 * @author diegosneves
 */
public interface ProductEntityService {

    /**
     * Busca um {@link diegosneves.github.hexagonal.app.domain.product.entity.Product Produto} com o {@link java.util.UUID ID} fornecido.
     *
     * @param id O ID do produto a ser buscado.
     * @return O {@link ProductEntityDTO} com o ID fornecido.
     * @throws ProductException Se o produto com o ID fornecido não existir.
     */
    ProductEntityDTO get(String id) throws ProductException;

    /**
     * Cria um novo {@link diegosneves.github.hexagonal.app.domain.product.entity.Product Produto} com o nome e preço fornecidos.
     *
     * @param productName O nome do produto.
     * @param productPrice O preço do produto.
     * @return O {@link ProductEntityDTO} criado.
     */
    ProductEntityDTO create(String productName, Double productPrice);

    /**
     * Habilita um {@link diegosneves.github.hexagonal.app.domain.product.entity.Product Produto} com o {@link java.util.UUID ID} fornecido.
     *
     * @param id O ID do produto a ser habilitado.
     * @return O {@link ProductEntityDTO} habilitado.
     * @throws ProductException Se o produto com o ID fornecido não existir.
     */
    ProductEntityDTO enable(String id);

    /**
     * Desabilita um {@link diegosneves.github.hexagonal.app.domain.product.entity.Product Produto} com o {@link java.util.UUID ID} fornecido.
     *
     * @param id O ID do produto a ser desabilitado.
     * @return O {@link ProductEntityDTO} desabilitado.
     * @throws ProductException Se o produto com o ID fornecido não existir.
     */
    ProductEntityDTO disable(String id);

}
