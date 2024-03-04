package diegosneves.github.hexagonal.adapters.repository;

import diegosneves.github.hexagonal.adapters.model.ProductEntity;
import diegosneves.github.hexagonal.app.domain.product.entity.Product;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.app.exceptions.ProductException;
import diegosneves.github.hexagonal.app.exceptions.handler.ExceptionHandler;
import diegosneves.github.hexagonal.adapters.mapper.MapperStrategy;
import diegosneves.github.hexagonal.adapters.mapper.ProductEntityMapper;
import diegosneves.github.hexagonal.adapters.mapper.ProductMapper;
import diegosneves.github.hexagonal.app.repository.ProductPersistenceContract;
import org.springframework.data.repository.CrudRepository;

import static java.util.Objects.isNull;

public interface ProductRepository extends ProductPersistenceContract, CrudRepository<ProductEntity, String> {

    @Override
    default ProductContract get(String id) {
        if (isNull(id) || id.trim().isBlank()) {
            throw new ProductException(ExceptionHandler.PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY);
        }
        MapperStrategy<Product, ProductEntity> mapperStrategy = new ProductMapper();
        ProductEntity productEntity = findById(id.trim()).orElseThrow(() -> new ProductException(ExceptionHandler.PRODUCT_NOT_FOUND, id.trim()));
        return mapperStrategy.mapper(productEntity);
    }

    @Override
    default ProductContract save(ProductContract product) {
        if (isNull(product)) {
            throw new ProductException(ExceptionHandler.PRODUCT_SHOULD_NOT_BE_NULL);
        }
        MapperStrategy<Product, ProductEntity> productToEntityMapper = new ProductMapper();
        MapperStrategy<ProductEntity, ProductContract> entityToProductMapper = new ProductEntityMapper();

        ProductEntity productEntity = entityToProductMapper.mapper(product);
        return productToEntityMapper.mapper(save(productEntity));
    }
}
