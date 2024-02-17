package diegosneves.github.hexagonal.service;

import diegosneves.github.hexagonal.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.domain.product.factory.ProductFactory;
import diegosneves.github.hexagonal.exceptions.ProductException;
import diegosneves.github.hexagonal.exceptions.handler.ExceptionHandler;
import diegosneves.github.hexagonal.repository.ProductPersistenceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

/**
 * A classe {@link ProductService} é responsável por gerenciar {@link diegosneves.github.hexagonal.domain.product.entity.Product produtos}, incluindo a recuperação, criação, ativação e desativação deles.
 * Ela implementa a interface {@link ProductServiceContract}.
 *
 * @author diegoneves
 */
@Service
public class ProductService implements ProductServiceContract {

    private ProductPersistenceContract persistence;

    /**
    * O construtor do ProductService onde se define o repositório(permanent storage) que está sendo usado
    *
    * @param persistence Uma instância implementando ProductPersistenceContract que será usada para operações de CRUD
    */
    @Autowired
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
        return ProductFactory.create(productName, productPrice);
    }

    @Override
    public ProductContract enable(ProductContract product) {
        product.enable();
        return product;
    }

    @Override
    public ProductContract disable(ProductContract product) {
        product.disable();
        return product;
    }
}
