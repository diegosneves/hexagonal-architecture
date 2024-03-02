package diegosneves.github.hexagonal.app.mapper;

import diegosneves.github.hexagonal.adapters.model.ProductEntity;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;

public class ProductEntityMapper implements MapperStrategy<ProductEntity, ProductContract> {

    @Override
    public ProductEntity mapper(ProductContract origin) {
        return new ProductEntity(origin.getId(), origin.getName(), origin.getStatus(), origin.getPrice());
    }
}
