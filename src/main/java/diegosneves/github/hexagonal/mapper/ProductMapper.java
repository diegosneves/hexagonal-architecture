package diegosneves.github.hexagonal.mapper;

import diegosneves.github.hexagonal.adapters.model.ProductEntity;
import diegosneves.github.hexagonal.domain.product.entity.Product;
import diegosneves.github.hexagonal.enums.ProductStatus;

public class ProductMapper implements MapperStrategy<Product, ProductEntity> {

    @Override
    public Product mapper(ProductEntity origin) {
        Product product = new Product(origin.getId(), origin.getProductName(), origin.getProductPrice());
        if (origin.getStatus() == ProductStatus.ENABLE) {
            product.enable();
        }
        return product;
    }
}
