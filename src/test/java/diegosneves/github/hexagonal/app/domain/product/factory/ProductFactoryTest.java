package diegosneves.github.hexagonal.app.domain.product.factory;

import diegosneves.github.hexagonal.app.domain.product.entity.Product;
import diegosneves.github.hexagonal.app.domain.product.factory.ProductFactory;
import diegosneves.github.hexagonal.app.enums.ProductStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ProductFactoryTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCreateProductValid() {
        Product product = ProductFactory.create("Produto", 12.0);

        assertNotNull(product);
        assertTrue(product.isValid());
        assertEquals("Produto", product.getName());
        assertEquals(12.0, product.getPrice());
        assertEquals(ProductStatus.DISABLE, product.getStatus());
    }
}
