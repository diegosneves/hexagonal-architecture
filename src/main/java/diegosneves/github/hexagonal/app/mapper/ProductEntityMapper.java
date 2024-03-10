package diegosneves.github.hexagonal.app.mapper;

import diegosneves.github.hexagonal.adapters.rest.model.ProductEntity;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;

/**
 * A classe {@link ProductEntityMapper} realiza a transformação de um objeto {@link ProductContract} em um objeto {@link ProductEntity}.
 * Através da implementação da interface {@link MapperStrategy}, esta classe define como essa transformação entre os dois tipos de objeto será executada.
 * <p>
 * Este processo é essencial para a correta conversão dos dados do contrato do produto para a sua representação no banco de dados, permitindo a correta persistência e manipulação dos mesmos.
 *
 * @author diegosneves
 */
public class ProductEntityMapper implements MapperStrategy<ProductEntity, ProductContract> {

    @Override
    public ProductEntity mapper(ProductContract origin) {
        return new ProductEntity(origin.getId(), origin.getName(), origin.getStatus(), origin.getPrice());
    }
}
