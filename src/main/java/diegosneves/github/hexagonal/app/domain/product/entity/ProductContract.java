package diegosneves.github.hexagonal.app.domain.product.entity;

import diegosneves.github.hexagonal.app.enums.ProductStatus;
import diegosneves.github.hexagonal.app.exceptions.ProductException;

/**
 * A interface {@link ProductContract} define o contrato para um objeto do tipo Produto.
 * Ela fornece métodos para verificar se o produto é válido, habilitar ou desabilitar o produto,
 * obter o ID do produto, nome, status e preço.
 *
 * @author diegosneves
 */
public interface ProductContract {

    /**
     * Verifica se o objeto {@link Product Produto} é válido.
     *
     * @return {@link Boolean#TRUE true} se o objeto Produto for válido, {@link Boolean#FALSE false} caso contrário.
     * @throws ProductException se qualquer validação falhar.
     */
    Boolean isValid() throws ProductException;

    /**
     * Ativa o {@link Product produto}.
     * <p>
     * Este método é usado para ativar o produto. Uma vez ativado, o produto estará
     * elegível para uso. Não retorna nenhum valor.
     *
     * @throws ProductException se o preço do produto for menor ou igual a zero.
     * @see ProductContract#disable() disable
     */
    void enable() throws ProductException;

    /**
     * Desativa o {@link Product produto}.
     *
     * @throws ProductException se o preço do produto for maior que zero.
     * @see ProductContract#enable() enable
     */
    void disable() throws ProductException;

    /**
     * Retorna o {@link java.util.UUID ID} do {@link Product produto}.
     *
     * @return O ID do produto como uma String.
     */
    String getId();

    /**
     * Devolve o nome do {@link Product produto}.
     *
     * @return O nome do produto como uma String.
     */
    String getName();

    /**
     * Obtém o status do {@link Product produto}.
     *
     * @return O status do produto como um valor de {@link ProductStatus}.
     */
    ProductStatus getStatus();

    /**
     * Obtém o preço do {@link Product produto}.
     *
     * @return O preço do produto como um valor Double.
     */
    Double getPrice();

}
