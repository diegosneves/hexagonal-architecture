package diegosneves.github.hexagonal.domain.product.entity;

import diegosneves.github.hexagonal.enums.ProductStatus;
import diegosneves.github.hexagonal.exceptions.ProductException;
import diegosneves.github.hexagonal.exceptions.handler.ExceptionHandler;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ProductTest {

    private static final String UUID = "0ed6e6a1-882c-4d2d-83ee-67034ee1ab9f";
    private Product product;

    @BeforeEach
    void setUp() {

        this.product = new Product(UUID, "Produto I", 10.0);

    }

    @Test
    void shouldReturnProductIdWhenGetIdIsCalled() {
        assertEquals(UUID, this.product.getId());
    }

    @Test
    void shouldReturnProductNameWhenGetNameIsCalled() {
        assertEquals("Produto I", this.product.getName());
    }

    @Test
    void shouldReturnProductStatusWhenGetStatusIsCalled() {
        assertEquals(ProductStatus.DISABLE, this.product.getStatus());
    }

    @Test
    void shouldReturnProductPriceWhenGetPriceIsCalled() {
        assertEquals(10.0, this.product.getPrice());
    }

    @Test
    void shouldEnableProductWhenPriceIsGreaterThanZero() {
        this.product.enable();
        assertEquals(ProductStatus.ENABLE, this.product.getStatus());
    }

    @Test
    @SneakyThrows
    void shouldThrowProductExceptionWhenPriceIsEqualZero() {
        Field field = this.product.getClass().getDeclaredField("productPrice");
        field.setAccessible(true);

        field.set(this.product, 0.0);
        ProductException exception = assertThrows(ProductException.class, () -> this.product.enable());

        assertEquals(ExceptionHandler.PRICE_LESS_THAN_ZERO.getMessage(), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldThrowProductExceptionWhenPriceIsLessThanZero() {
        Field field = this.product.getClass().getDeclaredField("productPrice");
        field.setAccessible(true);

        field.set(this.product, -1.0);
        ProductException exception = assertThrows(ProductException.class, () -> this.product.enable());

        assertEquals(ExceptionHandler.PRICE_LESS_THAN_ZERO.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowProductExceptionWhenPriceIsGreaterThanZero() {
        ProductException exception = assertThrows(ProductException.class, () -> this.product.disable());

        assertEquals(ExceptionHandler.PRICE_EQUAL_OR_LESS_THAN_ZERO.getMessage(), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldDisableProductWhenPriceIsEqualZero() {
        Field field = this.product.getClass().getDeclaredField("productPrice");
        field.setAccessible(true);

        field.set(this.product, 0.0);
        this.product.disable();

        assertEquals(ProductStatus.DISABLE, this.product.getStatus());
    }

    @Test
    @SneakyThrows
    void shouldDisableProductWhenPriceIsLessThanZero() {
        Field field = this.product.getClass().getDeclaredField("productPrice");
        field.setAccessible(true);

        field.set(this.product, -1.0);
        this.product.disable();

        assertEquals(ProductStatus.DISABLE, this.product.getStatus());
    }

    @Test
    @SneakyThrows
    void privateMethodShouldThrowProdutoExceptionWhenProductIdIsNotFormattedAsUUID() {
        String productId = "p001";
        Method method = this.product.getClass().getDeclaredMethod("isValidUUID");
        method.setAccessible(true);

        Field field = this.product.getClass().getDeclaredField("id");
        field.setAccessible(true);

        field.set(this.product, productId);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.product));

        assertNotNull(exception);
        assertInstanceOf(ProductException.class, exception.getTargetException());
        assertEquals(ExceptionHandler.INVALID_UUID_FORMAT_MESSAGE.getMessage(productId), exception.getTargetException().getMessage());
    }

    @Test
    @SneakyThrows
    void shouldThrowProdutoExceptionWhenProductIdIsNull() {
        Field field = this.product.getClass().getDeclaredField("id");
        field.setAccessible(true);

        field.set(this.product, null);

        ProductException exception = assertThrows(ProductException.class, () -> this.product.isValid());

        assertEquals(ExceptionHandler.PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY.getMessage(), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldThrowProdutoExceptionWhenProductIdIsNotFormattedAsUUID() {
        Field field = this.product.getClass().getDeclaredField("id");
        field.setAccessible(true);

        String productId = "p001";
        field.set(this.product, productId);

        ProductException exception = assertThrows(ProductException.class, () -> this.product.isValid());

        assertEquals(ExceptionHandler.INVALID_UUID_FORMAT_MESSAGE.getMessage(productId), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldThrowProdutoExceptionWhenProductIdIsEmpty() {
        Field field = this.product.getClass().getDeclaredField("id");
        field.setAccessible(true);

        field.set(this.product, "");

        ProductException exception = assertThrows(ProductException.class, () -> this.product.isValid());

        assertEquals(ExceptionHandler.PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY.getMessage(), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldThrowProdutoExceptionWhenProductIdIsBlank() {
        Field field = this.product.getClass().getDeclaredField("id");
        field.setAccessible(true);

        field.set(this.product, "  ");

        ProductException exception = assertThrows(ProductException.class, () -> this.product.isValid());

        assertEquals(ExceptionHandler.PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY.getMessage(), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldThrowProdutoExceptionWhenProductNameIsNull() {
        Field field = this.product.getClass().getDeclaredField("productName");
        field.setAccessible(true);

        field.set(this.product, null);

        ProductException exception = assertThrows(ProductException.class, () -> this.product.isValid());

        assertEquals(ExceptionHandler.PRODUCT_NAME_SHOULD_NOT_BE_NULL_OR_EMPTY.getMessage(), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldThrowProdutoExceptionWhenProductNameIsEmpty() {
        Field field = this.product.getClass().getDeclaredField("productName");
        field.setAccessible(true);

        field.set(this.product, "");

        ProductException exception = assertThrows(ProductException.class, () -> this.product.isValid());

        assertEquals(ExceptionHandler.PRODUCT_NAME_SHOULD_NOT_BE_NULL_OR_EMPTY.getMessage(), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldThrowProdutoExceptionWhenProductNameIsBlank() {
        Field field = this.product.getClass().getDeclaredField("productName");
        field.setAccessible(true);

        field.set(this.product, "  ");

        ProductException exception = assertThrows(ProductException.class, () -> this.product.isValid());

        assertEquals(ExceptionHandler.PRODUCT_NAME_SHOULD_NOT_BE_NULL_OR_EMPTY.getMessage(), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldThrowProdutoExceptionWhenProductStatusIsNull() {
        Field field = this.product.getClass().getDeclaredField("status");
        field.setAccessible(true);

        field.set(this.product, null);

        ProductException exception = assertThrows(ProductException.class, () -> this.product.isValid());

        assertEquals(ExceptionHandler.PRODUCT_STATUS_SHOULD_NOT_BE_NULL.getMessage(), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldBeTrueWhenProductPriceIsEqualZero() {
        Field field = this.product.getClass().getDeclaredField("productPrice");
        field.setAccessible(true);

        field.set(this.product, 0.0);

        Boolean productValid = this.product.isValid();

        assertTrue(productValid);
    }

    @Test
    @SneakyThrows
    void shouldThrowProductExceptionWhenProductPriceIsLessThanZero() {
        Field field = this.product.getClass().getDeclaredField("productPrice");
        field.setAccessible(true);

        field.set(this.product, -1.0);
        ProductException exception = assertThrows(ProductException.class, () -> this.product.isValid());

        assertEquals(ExceptionHandler.PRICE_LESS_THAN_ZERO.getMessage(), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldThrowProductExceptionWhenProductPriceIsnull() {
        Field field = this.product.getClass().getDeclaredField("productPrice");
        field.setAccessible(true);

        field.set(this.product, null);
        ProductException exception = assertThrows(ProductException.class, () -> this.product.isValid());

        assertEquals(ExceptionHandler.PRICE_LESS_THAN_ZERO.getMessage(), exception.getMessage());
    }

}
