package diegosneves.github.hexagonal.app.domain.product.factory;

import diegosneves.github.hexagonal.app.enums.ProductStatus;
import diegosneves.github.hexagonal.app.domain.product.entity.Product;

import java.util.UUID;

/**
 * A classe {@link ProductFactory} oferece um método de fábrica para criar instâncias da classe {@link Product}.
 * <p>
 * Esta classe é responsável por gerar novos objetos {@link Product} com um {@link UUID identificador único} e o {@link String nome}, {@link ProductStatus status} e {@link Double preço} especificados.
 *
 * @author diegosneves
 */
public class ProductFactory {

    private ProductFactory() {

    }

    /**
     * Método de criação que gera um novo {@link Product produto} com {@link UUID identificador único}, {@link String nome}, {@link ProductStatus status} e {@link Double preço}.
     *
     * @param name         Nome do produto.
     * @param status       Status do produto.
     * @param productPrice Preço do produto.
     * @return Retorna a nova instância do {@link Product Produto}.
     */
    public static Product create(String name, Double productPrice) {
        return new Product(UUID.randomUUID().toString(), name, productPrice);
    }

}
