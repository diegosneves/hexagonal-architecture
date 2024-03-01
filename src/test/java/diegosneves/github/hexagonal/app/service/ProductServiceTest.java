package diegosneves.github.hexagonal.app.service;

import diegosneves.github.hexagonal.app.domain.product.entity.Product;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.app.enums.ProductStatus;
import diegosneves.github.hexagonal.app.exceptions.ProductException;
import diegosneves.github.hexagonal.app.exceptions.handler.ExceptionHandler;
import diegosneves.github.hexagonal.app.repository.ProductPersistenceContract;
import diegosneves.github.hexagonal.app.service.ProductService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    private static final String UUID = "0ed6e6a1-882c-4d2d-83ee-67034ee1ab9f";

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductPersistenceContract persistence;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    private ProductContract product;

    @BeforeEach
    void setUp() {

        this.product = new Product(UUID, "Produto", 100.0);

    }

    @Test
    void shouldReturnProductWhenProductIdGiven() {
        when(this.persistence.get(anyString())).thenReturn(this.product);
        ProductContract result = this.service.get(UUID);
        assertEquals(this.product, result);
    }

    @Test
    @SneakyThrows
    void givenNullProductIdWhenGetProductThenThrowsProductException() {

        Exception result = assertThrows(ProductException.class, () -> this.service.get(null));

        assertNotNull(result);
        assertInstanceOf(ProductException.class, result);
        assertEquals(ExceptionHandler.PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY.getMessage(), result.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldThrowProductExceptionWhenProductIdIsBlank() {

        Exception result = assertThrows(ProductException.class, () -> this.service.get(" "));

        assertNotNull(result);
        assertInstanceOf(ProductException.class, result);
        assertEquals(ExceptionHandler.PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY.getMessage(), result.getMessage());
    }

    @Test
    void shouldCreateProductWhenValidProductNameAndProductPriceGiven() {
        String productName = "New Product";
        Double productPrice = 200.0;
        when(this.persistence.save(any(Product.class))).thenReturn(this.product);

        ProductContract result = this.service.create(productName, productPrice);

        verify(this.persistence, times(1)).save(this.productCaptor.capture());

        assertNotNull(result);
        assertEquals(this.product, result);
        assertTrue(this.isUUID(this.productCaptor.getValue().getId()));
        assertEquals(productName, this.productCaptor.getValue().getName());
        assertEquals(productPrice, this.productCaptor.getValue().getPrice());
        assertEquals(ProductStatus.DISABLE, this.productCaptor.getValue().getStatus());
    }

    private boolean isUUID(String uuid) {
        boolean isUuid = false;
        try {
            if (uuid != null && !(uuid.isBlank())) {
                java.util.UUID.fromString(uuid);
                isUuid = true;
            }
        } catch (Exception ignored) {
        }
        return isUuid;
    }

    @Test
    void shouldThrowProductExceptionWhenAttemptToCreateProductWithEmptyProductName() {
        String productName = "";
        Double productPrice = 200.0;

        Exception result = assertThrows(ProductException.class, () -> this.service.create(productName, productPrice));

        assertNotNull(result);
        assertInstanceOf(ProductException.class, result);
        assertEquals(ExceptionHandler.PRODUCT_NAME_SHOULD_NOT_BE_NULL_OR_EMPTY.getMessage(), result.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingProductWithNullPrice() {
        String productName = "New Product";

        Exception result = assertThrows(ProductException.class, () -> this.service.create(productName, null));

        assertNotNull(result);
        assertInstanceOf(ProductException.class, result);
        assertEquals(ExceptionHandler.PRICE_LESS_THAN_ZERO.getMessage(), result.getMessage());
    }

    @Test
    void shouldEnableProductAndAssertItsStatusIsEnabled() {
        when(this.persistence.save(this.product)).thenReturn(this.product);

        Product enable = (Product) this.service.enable(this.product);

        ProductStatus status = enable.getStatus();

        assertEquals(ProductStatus.ENABLE, status);
    }

    @Test
    void shouldThrowProductExceptionWhenAttemptingToEnableProductWithZeroPrice() {
        this.product = new Product(UUID, "Produto", 0.0);

        Exception exception = assertThrows(ProductException.class, () -> this.service.enable(this.product));

        assertNotNull(exception);
        assertInstanceOf(ProductException.class, exception);
        assertEquals(ExceptionHandler.PRICE_LESS_THAN_ZERO.getMessage(), exception.getMessage());
    }

    @Test
    void shouldDisableProductAndAssertItsStatusIsDisable() {
        this.product = new Product(UUID, "Produto", 0.0);
        when(this.persistence.save(this.product)).thenReturn(this.product);

        Product disabledProduct = (Product) this.service.disable(this.product);

        ProductStatus status = disabledProduct.getStatus();

        assertEquals(ProductStatus.DISABLE, status);
    }

    @Test
    void shouldThrowProductExceptionWhenAttemptingToDisableProductPrice() {

        Exception exception = assertThrows(ProductException.class, () -> this.service.disable(this.product));

        assertNotNull(exception);
        assertInstanceOf(ProductException.class, exception);
        assertEquals(ExceptionHandler.PRICE_EQUAL_OR_LESS_THAN_ZERO.getMessage(), exception.getMessage());
    }

}
