package diegosneves.github.hexagonal.adapters.cli.repository.impl;

import diegosneves.github.hexagonal.adapters.cli.repository.ProductEntityRepository;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.app.exceptions.ProductException;
import diegosneves.github.hexagonal.app.exceptions.handler.ExceptionHandler;

import static java.util.Objects.isNull;

public class ProductEntityRepositoryImpl implements ProductEntityRepository {

    @Override
    public ProductContract get(String id) {
        if (isNull(id) || id.trim().isBlank() ) {
            throw new ProductException(ExceptionHandler.PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY);
        }
        return null;
    }

    @Override
    public ProductContract save(ProductContract product) {
        if (isNull(product)) {
            throw new ProductException(ExceptionHandler.PRODUCT_SHOULD_NOT_BE_NULL);
        }
        return null;
    }
}
