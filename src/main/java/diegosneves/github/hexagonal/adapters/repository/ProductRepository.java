package diegosneves.github.hexagonal.adapters.repository;

import diegosneves.github.hexagonal.adapters.model.ProductEntity;
import diegosneves.github.hexagonal.domain.product.entity.Product;
import diegosneves.github.hexagonal.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.exceptions.ProductException;
import diegosneves.github.hexagonal.exceptions.handler.ExceptionHandler;
import diegosneves.github.hexagonal.mapper.MapperStrategy;
import diegosneves.github.hexagonal.mapper.ProductMapper;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, String> {

    default ProductContract get(String id) {
        MapperStrategy<Product, ProductEntity> mapperStrategy = new ProductMapper();
        ProductEntity productEntity = findById(id).orElseThrow(() -> new ProductException(ExceptionHandler.PRODUCT_NOT_FOUND, id));
        return mapperStrategy.mapper(productEntity);
    }

}
