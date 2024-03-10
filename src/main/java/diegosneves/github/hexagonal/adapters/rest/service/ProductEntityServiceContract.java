package diegosneves.github.hexagonal.adapters.rest.service;

import diegosneves.github.hexagonal.adapters.rest.request.ProductRequest;
import diegosneves.github.hexagonal.adapters.rest.response.ProductResponse;
import diegosneves.github.hexagonal.app.exceptions.ProductException;

/**
 * A interface {@link ProductEntityServiceContract} define o contrato para a gerência de objetos {@link diegosneves.github.hexagonal.adapters.rest.model.ProductEntity ProductEntity}.
 *
 * @author diegosneves
 */
public interface ProductEntityServiceContract {

    /**
     * Recupera as informações do produto com base no {@link java.util.UUID ID} do produto especificado.
     *
     * @param id O ID do produto.
     * @return O objeto {@link ProductResponse} contendo informações referentes ao produto.
     * @throws ProductException Se um erro ocorrer durante a recuperação do produto.
     */
    ProductResponse get(String id) throws ProductException;

    /**
     * Cria um novo produto com base na solicitação de produto fornecida.
     *
     * @param request A solicitação do produto contendo o nome e o preço do produto.
     * @return O objeto {@link ProductResponse} contendo informações referentes ao produto criado.
     */
    ProductResponse create(ProductRequest request);

    /**
     * Habilita um produto com o {@link java.util.UUID ID} especificado.
     *
     * @param id O ID do produto a ser habilitado.
     * @return O objeto {@link ProductResponse} contendo informações referentes ao produto habilitado.
     */
    ProductResponse enable(String id);

    /**
     * Desabilita um produto com o {@link java.util.UUID ID} especificado.
     *
     * @param id O ID do produto a ser desabilitado.
     * @return O objeto {@link ProductResponse} contendo informações referentes ao produto desabilitado.
     */
    ProductResponse disable(String id);

}
