package diegosneves.github.hexagonal.repository;

import diegosneves.github.hexagonal.domain.product.entity.ProductContract;

/**
 * A interface {@link  ProductWriter} é um contrato para classes que escrevem dados de produto.
 * Ela fornece um método para salvar um objeto do tipo {@link ProductContract}.
 *
 * @author diegoneves
 */
public interface ProductWriter {

    /**
     * Salva um produto.
     * <p>
     * Este método é utilizado para salvar um produto. Ele aceita um produto do tipo {@link ProductContract} como parâmetro
     * e retorna o produto que foi salvo.
     *
     * @param product O produto a ser salvo.
     * @return O produto salvo de tipo {@link ProductContract}.
     */
    ProductContract save(ProductContract product);

}
