package diegosneves.github.hexagonal.domain.product.entity;

import diegosneves.github.hexagonal.enums.ProductStatus;

/**
 * A interface {@link ProductContract} define o contrato para um objeto do tipo Produto.
 * Ela fornece métodos para verificar se o produto é válido, habilitar ou desabilitar o produto,
 * obter o ID do produto, nome, status e preço.
 *
 * @author diegosneves
 */
public interface ProductContract {

    /**
     * Determina se o produto é válido.
     *
     * @return {@code true} se o produto for válido, {@code false} caso contrário.
     */
    Boolean isValid();

    /**
     * Ativa o produto.
     * <p>
     * Este método é usado para ativar o produto. Uma vez ativado, o produto estará
     * elegível para uso. Não retorna nenhum valor.
     *
     * @see ProductContract#disable()
     */
    void enable();

    /**
     * Desativa o produto.
     *
     * <p>
     * Este método é utilizado para desativar o produto. Uma vez desativado, o produto não estará mais apto para uso.
     * A chamada deste método não retorna nenhum valor.
     * </p>
     *
     * @see ProductContract#enable()
     */
    void disable();

    /**
     * Retorna o ID do produto.
     *
     * @return O ID do produto como uma String.
     */
    String getId();

    /**
     * Devolve o nome do produto.
     *
     * @return O nome do produto como uma String.
     */
    String getName();

    /**
     * Obtém o status do produto.
     *
     * @return O status do produto como um valor de {@link ProductStatus}.
     */
    ProductStatus getStatus();

    /**
     * Obtém o preço do produto.
     *
     * @return O preço do produto como um valor Double.
     */
    Double getPrice();

}
