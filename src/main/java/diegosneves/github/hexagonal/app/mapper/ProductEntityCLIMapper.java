package diegosneves.github.hexagonal.app.mapper;

import diegosneves.github.hexagonal.adapters.cli.model.ProductEntity;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;

public class ProductEntityCLIMapper implements MapperStrategy<ProductEntity, ProductContract> {

    @Override
    public ProductEntity mapper(ProductContract origin) {
        return new ProductEntity.Builder()
                .id(origin.getId())
                .productName(origin.getName())
                .productPrice(origin.getPrice())
                .status(origin.getStatus())
                .build();
    }
}
