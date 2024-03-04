package diegosneves.github.hexagonal.adapters.service;

import diegosneves.github.hexagonal.adapters.repository.ProductRepository;
import diegosneves.github.hexagonal.adapters.request.ProductRequest;
import diegosneves.github.hexagonal.adapters.response.ProductResponse;
import diegosneves.github.hexagonal.app.domain.product.entity.Product;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.app.enums.ProductStatus;
import diegosneves.github.hexagonal.app.exceptions.ProductException;
import diegosneves.github.hexagonal.app.exceptions.handler.ExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

}
