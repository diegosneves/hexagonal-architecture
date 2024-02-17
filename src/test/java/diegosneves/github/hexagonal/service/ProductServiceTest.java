package diegosneves.github.hexagonal.service;

import diegosneves.github.hexagonal.domain.product.entity.Product;
import diegosneves.github.hexagonal.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.domain.product.factory.ProductFactory;
import diegosneves.github.hexagonal.enums.ProductStatus;
import diegosneves.github.hexagonal.exceptions.ProductException;
import diegosneves.github.hexagonal.exceptions.handler.ExceptionHandler;
import diegosneves.github.hexagonal.repository.ProductPersistenceContract;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    private static final String UUID = "0ed6e6a1-882c-4d2d-83ee-67034ee1ab9f";

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductPersistenceContract persistence;

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

        ProductContract result = this.service.create(productName, productPrice);

        assertNotNull(result);
        assertEquals(productName, result.getName());
        assertEquals(productPrice, result.getPrice());
        assertEquals(ProductStatus.DISABLE, result.getStatus());
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
    void shouldEnableProductAndAssertItsStatusIsEnabled(){
        Product enable = (Product) this.service.enable(this.product);

        ProductStatus status = enable.getStatus();

        assertEquals(ProductStatus.ENABLE, status);
    }

    @Test
    void shouldThrowProductExceptionWhenAttemptingToEnableProductWithZeroPrice(){
        this.product = new Product(UUID, "Produto", 0.0);

        Exception exception = assertThrows(ProductException.class, () -> this.service.enable(this.product));

        assertNotNull(exception);
        assertInstanceOf(ProductException.class, exception);
        assertEquals(ExceptionHandler.PRICE_LESS_THAN_ZERO.getMessage(), exception.getMessage());
    }

    @Test
    void shouldDisableProductAndAssertItsStatusIsDisable(){
        this.product = new Product(UUID, "Produto", 0.0);

        Product disabledProduct = (Product) this.service.disable(this.product);

        ProductStatus status = disabledProduct.getStatus();

        assertEquals(ProductStatus.DISABLE, status);
    }

    @Test
    void shouldThrowProductExceptionWhenAttemptingToDisableProductPrice(){

        Exception exception = assertThrows(ProductException.class, () -> this.service.disable(this.product));

        assertNotNull(exception);
        assertInstanceOf(ProductException.class, exception);
        assertEquals(ExceptionHandler.PRICE_EQUAL_OR_LESS_THAN_ZERO.getMessage(), exception.getMessage());
    }

}
