package diegosneves.github.hexagonal.service;

import diegosneves.github.hexagonal.domain.product.entity.Product;
import diegosneves.github.hexagonal.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.domain.product.factory.ProductFactory;
import diegosneves.github.hexagonal.exceptions.ProductException;
import diegosneves.github.hexagonal.exceptions.handler.ExceptionHandler;
import diegosneves.github.hexagonal.repository.ProductPersistenceContract;

import static java.util.Objects.isNull;

/**
 * A classe {@link ProductService} é responsável por gerenciar {@link diegosneves.github.hexagonal.domain.product.entity.Product produtos}, incluindo a recuperação, criação, ativação e desativação deles.
 * Ela implementa a interface {@link ProductServiceContract}.
 *
 * @author diegoneves
 */
public class ProductService implements ProductServiceContract {

    private final ProductPersistenceContract persistence;

    /**
    * O construtor do ProductService onde se define o repositório(permanent storage) que está sendo usado
    *
    * @param persistence Uma instância implementando ProductPersistenceContract que será usada para operações de CRUD
    */
    public ProductService(ProductPersistenceContract persistence) {
        this.persistence = persistence;
    }

    @Override
    public ProductContract get(String id) throws ProductException {
        if (isNull(id) || id.isBlank()) {
            throw new ProductException(ExceptionHandler.PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY);
        }
        return this.persistence.get(id);
    }

    @Override
    public ProductContract create(String productName, Double productPrice) {
        Product newProduct = ProductFactory.create(productName, productPrice);
        return this.persistence.save(newProduct);
    }

    @Override
    public ProductContract enable(ProductContract product) {
        product.enable();
        return this.persistence.save(product);
    }

    @Override
    public ProductContract disable(ProductContract product) {
        product.disable();
        return this.persistence.save(product);
    }
}
