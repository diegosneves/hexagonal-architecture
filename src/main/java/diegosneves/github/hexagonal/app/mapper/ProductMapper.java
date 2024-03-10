package diegosneves.github.hexagonal.app.mapper;

import diegosneves.github.hexagonal.adapters.rest.model.ProductEntity;
import diegosneves.github.hexagonal.app.domain.product.entity.Product;

import java.lang.reflect.Field;

/**
 * A classe {@link ProductMapper} implementa a interface {@link MapperStrategy} para definir uma estratégia de mapeamento entre um objeto {@link Product} e um objeto {@link ProductEntity}.
 *
 * @author diegosneves
 */
public class ProductMapper implements MapperStrategy<Product, ProductEntity> {

    private static final String STATUS_FIELD = "status";

    @Override
    public Product mapper(ProductEntity origin) {
        Product product = new Product(origin.getId(), origin.getProductName(), origin.getProductPrice());
        try {
            Field field = Product.class.getDeclaredField(STATUS_FIELD);
            field.setAccessible(true);
            field.set(product, origin.getStatus());
        } catch (NoSuchFieldException | IllegalAccessException ignored) {

        }
        return product;
    }
}
