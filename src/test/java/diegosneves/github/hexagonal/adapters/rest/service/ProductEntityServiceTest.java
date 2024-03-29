package diegosneves.github.hexagonal.adapters.rest.service;

import diegosneves.github.hexagonal.adapters.rest.repository.ProductRepository;
import diegosneves.github.hexagonal.adapters.rest.request.ProductRequest;
import diegosneves.github.hexagonal.adapters.rest.response.ProductResponse;
import diegosneves.github.hexagonal.app.domain.product.entity.Product;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.app.enums.ProductStatus;
import diegosneves.github.hexagonal.app.exceptions.ProductException;
import diegosneves.github.hexagonal.app.exceptions.handler.ExceptionHandler;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductEntityServiceTest {

    private static final String PRODUCT_ID = "0ed6e6a1-882c-4d2d-83ee-67034ee1ab9f";
    @InjectMocks
    private ProductEntityService service;

    @Mock
    private ProductRepository productRepository;

    @Captor
    ArgumentCaptor<ProductContract> productContractCaptor;

    private ProductContract productContract;

    @BeforeEach
    void setUp() {
        this.productContract = new Product(PRODUCT_ID, "Test Product", 10.0);

    }

    @Test
    void testProductCreationWithValidRequest() {

        ProductRequest request = ProductRequest.builder()
                .productName("Test Product")
                .productPrice(10.0)
                .build();


        when(this.productRepository.save(any(ProductContract.class))).thenReturn(this.productContract);

        ProductResponse response = this.service.create(request);

        verify(this.productRepository, times(2)).save(this.productContractCaptor.capture());

        ProductContract actual = this.productContractCaptor.getValue();
        assertNotNull(response);
        assertEquals(PRODUCT_ID, response.getId());
        assertNotNull(actual);
        assertTrue(this.isValidUUID(actual.getId()));
        assertTrue(actual.isValid());
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
    void shouldThrowProductExceptionWhenCreationInvokedWithInvalidRequest() {

        ProductRequest request = ProductRequest.builder()
                .productName(" ")
                .productPrice(-10.0)
                .build();

        ProductException exception = assertThrows(ProductException.class, () -> this.service.create(request));

        verify(this.productRepository, never()).save(any(ProductContract.class));

        assertNotNull(exception);
        assertEquals(ExceptionHandler.PRODUCT_NAME_SHOULD_NOT_BE_NULL_OR_EMPTY.getMessage(), exception.getMessage());
    }

    @Test
    void shouldReturnProductResponseWithCorrectDataWhenGivenValidProductId() {
        ProductResponse expectedResponse = ProductResponse.builder()
                .id(PRODUCT_ID)
                .productName("Test Product")
                .status(ProductStatus.DISABLE)
                .productPrice(10.0)
                .build();

        when(this.productRepository.get(eq(PRODUCT_ID))).thenReturn(this.productContract);

        ProductResponse actualResponse = this.service.get(PRODUCT_ID);

        verify(this.productRepository, times(1)).get(eq(PRODUCT_ID));

        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getProductName(), actualResponse.getProductName());
        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
        assertEquals(expectedResponse.getProductPrice(), actualResponse.getProductPrice());
    }

    @Test
    void shouldThrowProductExceptionForInvalidProductId() {
        String id = "ID001";
        when(this.productRepository.get(eq(id))).thenThrow(new ProductException(ExceptionHandler.PRODUCT_NOT_FOUND, id));

        ProductException exception = assertThrows(ProductException.class, () -> this.service.get(id));

        verify(this.productRepository, times(1)).get(anyString());

        assertNotNull(exception);
        assertEquals(ExceptionHandler.PRODUCT_NOT_FOUND.getMessage(id), exception.getMessage());
    }

    @Test
    void shouldThrowProductExceptionWhenProductIdIsBlank() {
        when(this.productRepository.get(anyString())).thenThrow(new ProductException(ExceptionHandler.PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY));

        ProductException exception = assertThrows(ProductException.class, () -> this.service.get(anyString()));

        verify(this.productRepository, times(1)).get(anyString());

        assertNotNull(exception);
        assertEquals(ExceptionHandler.PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY.getMessage(), exception.getMessage());
    }

    @Test
    void shouldEnableProductAndReturnProductResponseWhenValidIdIsProvided() {
        when(this.productRepository.save(any(ProductContract.class))).thenReturn(this.productContract);
        when(this.productRepository.get(eq(PRODUCT_ID))).thenReturn(this.productContract);

        ProductResponse response = this.service.enable(PRODUCT_ID);

        verify(this.productRepository, times(1)).get(eq(PRODUCT_ID));
        verify(this.productRepository, times(2)).save(this.productContractCaptor.capture());

        ProductContract actual = this.productContractCaptor.getValue();
        assertNotNull(response);
        assertEquals(PRODUCT_ID, actual.getId());
        assertEquals(ProductStatus.ENABLE, actual.getStatus());
    }


    @Test
    void shouldThrowExceptionWhenAttemptingToEnableProductWithZeroPrice() {
        this.productContract = new Product(PRODUCT_ID, "Product Name", 0.0);
        when(this.productRepository.get(eq(PRODUCT_ID))).thenReturn(this.productContract);

        ProductException exception = assertThrows(ProductException.class, () -> this.service.enable(PRODUCT_ID));

        verify(this.productRepository, times(1)).get(eq(PRODUCT_ID));
        verify(this.productRepository, never()).save(this.productContract);

        assertNotNull(exception);
        assertEquals(ExceptionHandler.PRICE_LESS_THAN_ZERO.getMessage(), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldDeactivateProductVerifyItAndReturnExpectedResponseForValidProductId() {
        this.productContract.enable();
        Field field = this.productContract.getClass().getDeclaredField("productPrice");
        field.setAccessible(true);
        field.set(this.productContract, 0.0);
        when(this.productRepository.save(any(ProductContract.class))).thenReturn(this.productContract);
        when(this.productRepository.get(eq(PRODUCT_ID))).thenReturn(this.productContract);

        ProductResponse response = this.service.disable(PRODUCT_ID);

        verify(this.productRepository, times(1)).get(eq(PRODUCT_ID));
        verify(this.productRepository, times(2)).save(this.productContractCaptor.capture());

        ProductContract actual = this.productContractCaptor.getValue();
        assertNotNull(response);
        assertEquals(PRODUCT_ID, actual.getId());
        assertEquals(ProductStatus.DISABLE, actual.getStatus());
    }


    @Test
    void shouldThrowProductExceptionWhenAttemptingToDisableProductWithNonFreePrice() {
        this.productContract = new Product(PRODUCT_ID, "Product Name", 1.0);
        when(this.productRepository.get(eq(PRODUCT_ID))).thenReturn(this.productContract);

        ProductException exception = assertThrows(ProductException.class, () -> this.service.disable(PRODUCT_ID));

        verify(this.productRepository, times(1)).get(eq(PRODUCT_ID));
        verify(this.productRepository, never()).save(this.productContract);

        assertNotNull(exception);
        assertEquals(ExceptionHandler.PRICE_EQUAL_OR_LESS_THAN_ZERO.getMessage(), exception.getMessage());
    }

}
