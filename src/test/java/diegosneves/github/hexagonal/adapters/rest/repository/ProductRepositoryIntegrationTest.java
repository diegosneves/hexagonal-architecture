package diegosneves.github.hexagonal.adapters.rest.repository;

import diegosneves.github.hexagonal.adapters.rest.model.ProductEntity;
import diegosneves.github.hexagonal.app.domain.product.entity.Product;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.app.enums.ProductStatus;
import diegosneves.github.hexagonal.app.exceptions.ProductException;
import diegosneves.github.hexagonal.app.exceptions.handler.ExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class ProductRepositoryIntegrationTest {

    private static final String PRODUCT_UUID = "0ed6e6a1-882c-4d2d-83ee-67034ee1ab9f";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository repository;

    private ProductEntity productEntity;
    @BeforeEach
    void setUp() {
        this.productEntity = new ProductEntity(PRODUCT_UUID, "Produto", ProductStatus.ENABLE, 200.0);
    }

    @Test
    void whenFindByIDThenReturnProductEntity() {
        // given
        this.entityManager.persist(this.productEntity);
        this.entityManager.flush();

        // when
        ProductContract found = this.repository.get(PRODUCT_UUID);

        // then
        assertEquals("Produto", found.getName());
        assertEquals(ProductStatus.ENABLE, found.getStatus());
        assertEquals(200.0, found.getPrice());
        assertTrue(found.isValid());
    }

    @Test
    void whenReceiveInvalidIDThenThrowProductException() {
        String uuid = "1ed6e6a1-882c-4d2d-83ee-67034ee1ab9f";
        this.entityManager.persist(this.productEntity);
        this.entityManager.flush();

        ProductException exception = assertThrows(ProductException.class, () -> this.repository.get(uuid));

        assertEquals(ExceptionHandler.PRODUCT_NOT_FOUND.getMessage(uuid), exception.getMessage());
    }

    @Test
    void whenReceiveNullIDThenThrowProductException() {
        this.entityManager.persist(this.productEntity);
        this.entityManager.flush();

        ProductException exception = assertThrows(ProductException.class, () -> this.repository.get(null));

        assertEquals(ExceptionHandler.PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY.getMessage(), exception.getMessage());
    }

    @Test
    void whenReceiveBlankIDThenThrowProductException() {
        this.entityManager.persist(this.productEntity);
        this.entityManager.flush();

        ProductException exception = assertThrows(ProductException.class, () -> this.repository.get(" "));

        assertEquals(ExceptionHandler.PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY.getMessage(), exception.getMessage());
    }

    @Test
    void whenSaveProductThenReturnSavedProduct() {
        Product product = new Product(PRODUCT_UUID, "Produto", 200.0);

        ProductContract saved = this.repository.save(product);

        assertNotNull(saved.getId());
        assertEquals(product.getId(), saved.getId());
        assertEquals("Produto", saved.getName());
        assertEquals(ProductStatus.DISABLE, saved.getStatus());
        assertEquals(200.0, saved.getPrice());
        assertTrue(saved.isValid());
    }

    @Test
    void whenModifyAndSaveProductStatusThenReturnSavedProductUpdate() {
        this.productEntity.setStatus(ProductStatus.DISABLE);
        this.entityManager.persist(this.productEntity);
        this.entityManager.flush();

        ProductContract found = this.repository.get(PRODUCT_UUID);
        found.enable();

        ProductContract saved = this.repository.save(found);

        assertNotNull(saved.getId());
        assertEquals(found.getId(), saved.getId());
        assertEquals("Produto", saved.getName());
        assertEquals(ProductStatus.ENABLE, saved.getStatus());
        assertEquals(200.0, saved.getPrice());
        assertTrue(saved.isValid());
    }

    @Test
    void whenReceiveNullProductThenThrowProductException() {

        ProductException exception = assertThrows(ProductException.class, () -> this.repository.save((ProductContract) null));

        assertEquals(ExceptionHandler.PRODUCT_SHOULD_NOT_BE_NULL.getMessage(), exception.getMessage());
    }

}
