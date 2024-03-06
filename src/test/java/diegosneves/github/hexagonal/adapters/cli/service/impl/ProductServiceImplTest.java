package diegosneves.github.hexagonal.adapters.cli.service.impl;

import diegosneves.github.hexagonal.adapters.cli.dto.ProductEntityDTO;
import diegosneves.github.hexagonal.adapters.cli.repository.impl.ProductEntityRepositoryImpl;
import diegosneves.github.hexagonal.app.domain.product.entity.Product;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.app.enums.ProductStatus;
import diegosneves.github.hexagonal.app.exceptions.ProductException;
import diegosneves.github.hexagonal.app.exceptions.handler.ExceptionHandler;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    private static final String PRODUCT_ID = "0ed6e6a1-882c-4d2d-83ee-67034ee1ab9f";

    private ProductServiceImpl service;

    ProductEntityRepositoryImpl repository;

    ArgumentCaptor<ProductContract> productContractCaptor = ArgumentCaptor.forClass(ProductContract.class);

    private Product product;

    @BeforeEach
    @SneakyThrows
    void setUp() {
        this.service = new ProductServiceImpl();
        this.repository = Mockito.mock(ProductEntityRepositoryImpl.class);
        Field field = this.service.getClass().getDeclaredField("repository");
        field.setAccessible(true);
        field.set(this.service, repository);

        this.product = new Product(PRODUCT_ID, "Product", 10.0);
    }

    @Test
    void shouldReturnValidProductEntityDTOWhenGivenValidId() {
        when(this.repository.get(PRODUCT_ID)).thenReturn(this.product);
        ProductEntityDTO result = this.service.get(PRODUCT_ID);

        assertNotNull(result);
        assertEquals(PRODUCT_ID, result.getId());
        assertEquals("Product", result.getProductName());
        assertEquals(ProductStatus.DISABLE, result.getStatus());
        assertEquals(10.0, result.getProductPrice());
    }


    @Test
    void shouldThrowProductExceptionOnRetrievalWithIdInvalid() {
        String id = "123";
        when(this.repository.get(id)).thenReturn(null);

        ProductException result = assertThrows(ProductException.class, () -> this.service.get(id));

        assertNotNull(result);
        assertEquals(ExceptionHandler.PRODUCT_NOT_FOUND.getMessage(id), result.getMessage());
    }

    @Test
    void shouldCreateProductGivenValidNameAndPrice() {
        double productPrice = this.product.getPrice();
        String productNameString = "Product";
        when(this.repository.save(any(ProductContract.class))).thenReturn(this.product);

        ProductEntityDTO result = this.service.create(productNameString, productPrice);

        verify(this.repository, times(1)).save(this.productContractCaptor.capture());

        assertNotNull(result);
        assertEquals(PRODUCT_ID, result.getId());
        assertEquals(productNameString, result.getProductName());
        assertEquals(ProductStatus.DISABLE, result.getStatus());
        assertEquals(productPrice, result.getProductPrice());
        ProductContract actual = this.productContractCaptor.getValue();
        assertNotNull(actual);
        assertTrue(isValidUUID(actual.getId()));
        assertEquals(productNameString, actual.getName());
        assertEquals(ProductStatus.DISABLE, actual.getStatus());
        assertEquals(productPrice, actual.getPrice());
    }

    @Test
    void shouldThrowExceptionWhenCreatingProductWithNegativePrice() {
        ProductException result = assertThrows(ProductException.class, () -> this.service.create("Product", -10.0));

        verify(this.repository, never()).save(any(ProductContract.class));

        assertNotNull(result);
        assertEquals(ExceptionHandler.PRICE_LESS_THAN_ZERO.getMessage(), result.getMessage());
    }

    private Boolean isValidUUID(String id) throws ProductException {
        try {
            UUID.fromString(id);
            return Boolean.TRUE;
        } catch (IllegalArgumentException ignored) {
            return Boolean.FALSE;
        }
    }

    @Test
    void shouldEnableProductGivenValidId() {
        when(this.repository.get(PRODUCT_ID)).thenReturn(this.product);
        when(this.repository.save(this.product)).thenReturn(this.product);

        ProductEntityDTO result = this.service.enable(PRODUCT_ID);

        verify(this.repository, times(1)).save(this.productContractCaptor.capture());

        assertNotNull(result);
        assertEquals(PRODUCT_ID, result.getId());
        assertEquals("Product", result.getProductName());
        assertEquals(ProductStatus.ENABLE, result.getStatus());
        assertEquals(10.0, result.getProductPrice());
        ProductContract actual = this.productContractCaptor.getValue();
        assertNotNull(actual);
        assertEquals(PRODUCT_ID, actual.getId());
        assertEquals("Product", actual.getName());
        assertEquals(ProductStatus.ENABLE, actual.getStatus());
        assertEquals(10.0, actual.getPrice());
    }

    @Test
    void shouldThrowProductExceptionWhenEnablingNonExistentProductGivenInvalidId() {
        String id = "123";
        when(this.repository.get(id)).thenReturn(null);

        ProductException result = assertThrows(ProductException.class, () -> this.service.enable(id));

        assertNotNull(result);
        assertEquals(ExceptionHandler.PRODUCT_NOT_FOUND.getMessage(id), result.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldDisableProductGivenValidId() {
        this.product.enable();
        Field field = this.product.getClass().getDeclaredField("productPrice");
        field.setAccessible(true);
        field.set(this.product, 0.0);
        when(this.repository.get(PRODUCT_ID)).thenReturn(this.product);
        when(this.repository.save(this.product)).thenReturn(this.product);

        ProductEntityDTO result = this.service.disable(PRODUCT_ID);

        verify(this.repository, times(1)).save(this.productContractCaptor.capture());

        assertNotNull(result);
        assertEquals(PRODUCT_ID, result.getId());
        assertEquals("Product", result.getProductName());
        assertEquals(ProductStatus.DISABLE, result.getStatus());
        assertEquals(0.0, result.getProductPrice());
        ProductContract actual = this.productContractCaptor.getValue();
        assertNotNull(actual);
        assertEquals(PRODUCT_ID, actual.getId());
        assertEquals("Product", actual.getName());
        assertEquals(ProductStatus.DISABLE, actual.getStatus());
        assertEquals(0.0, actual.getPrice());
    }


    @Test
    void shouldThrowProductExceptionWhenDisablingNonExistentProductGivenInvalidId() {
        String id = "123";
        when(this.repository.get(id)).thenReturn(null);

        ProductException result = assertThrows(ProductException.class, () -> this.service.disable(id));

        assertNotNull(result);
        assertEquals(ExceptionHandler.PRODUCT_NOT_FOUND.getMessage(id), result.getMessage());
    }

}
