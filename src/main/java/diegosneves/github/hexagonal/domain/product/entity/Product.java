package diegosneves.github.hexagonal.domain.product.entity;

import diegosneves.github.hexagonal.enums.ProductStatus;
import diegosneves.github.hexagonal.exceptions.ProductException;
import diegosneves.github.hexagonal.exceptions.handler.ExceptionHandler;

import java.util.UUID;

import static java.util.Objects.isNull;

/**
 * A classe {@link Product} representa um objeto do tipo "produto" com seu {@link UUID ID único}, {@link String nome}, {@link ProductStatus status} e {@link Double preço}.
 *
 * @author diegoneves
 */
public class Product implements ProductContract {

    private static final double ZERO = 0.0;
    private final String id;
    private String productName;
    private ProductStatus status;
    private Double productPrice;


    public Product(String id, String productName, Double productPrice) {
        this.id = id;
        this.productName = productName;
        this.status = ProductStatus.DISABLE;
        this.productPrice = productPrice;
        this.isValid();
    }

    @Override
    public Boolean isValid() throws ProductException {
        if (isNull(this.id) || this.id.isBlank()) {
            throw new ProductException(ExceptionHandler.PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY);
        }
        this.isValidUUID();
        if (isNull(this.productName) || this.productName.isBlank()){
            throw new ProductException(ExceptionHandler.PRODUCT_NAME_SHOULD_NOT_BE_NULL_OR_EMPTY);
        }
        if (isNull(this.status)) {
            throw new ProductException(ExceptionHandler.PRODUCT_STATUS_SHOULD_NOT_BE_NULL);
        }
        if (isNull(this.productPrice) || this.productPrice < ZERO) {
            throw new ProductException(ExceptionHandler.PRICE_LESS_THAN_ZERO);
        }
        return Boolean.TRUE;
    }

    /**
     * Verifica se a string {@link UUID} fornecida é válida.
     *
     * @throws ProductException Se o formato {@link UUID} é inválido.
     */
    private void isValidUUID() throws ProductException {
    try {
        UUID.fromString(this.id);
    } catch (IllegalArgumentException ignored) {
        throw new ProductException(ExceptionHandler.INVALID_UUID_FORMAT_MESSAGE, this.id);
    }
}

    @Override
    public void enable() throws ProductException {
        if (this.productPrice <= ZERO) {
            throw new ProductException(ExceptionHandler.PRICE_LESS_THAN_ZERO);
        }
        this.status = ProductStatus.ENABLE;
    }

    @Override
    public void disable() throws ProductException{
        if (this.productPrice > ZERO) {
            throw new ProductException(ExceptionHandler.PRICE_EQUAL_OR_LESS_THAN_ZERO);
        }
        this.status = ProductStatus.DISABLE;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.productName;
    }

    @Override
    public ProductStatus getStatus() {
        return this.status;
    }

    @Override
    public Double getPrice() {
        return this.productPrice;
    }
}
