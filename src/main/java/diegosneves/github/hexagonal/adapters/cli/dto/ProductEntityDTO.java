package diegosneves.github.hexagonal.adapters.cli.dto;

import diegosneves.github.hexagonal.app.enums.ProductStatus;

/**
 * A classe {@link ProductEntityDTO} representa um objeto de transferência de dados (DTO) para uma entidade de produto.
 * Ela contém as informações básicas de um {@link diegosneves.github.hexagonal.adapters.cli.model.ProductEntity produto}, como ID, nome, status e preço.
 *
 * @author diegosneves
 */
public class ProductEntityDTO {

    private String id;
    private String productName;
    private ProductStatus status;
    private Double productPrice;

    public ProductEntityDTO() {
    }

    public ProductEntityDTO(String id, String productName, ProductStatus status, Double productPrice) {
        this.id = id;
        this.productName = productName;
        this.status = status;
        this.productPrice = productPrice;
    }

    public String getId() {
        return this.id;
    }

    public String getProductName() {
        return this.productName;
    }

    public ProductStatus getStatus() {
        return this.status;
    }

    public Double getProductPrice() {
        return this.productPrice;
    }

    @Override
    public String toString() {
        return String.format("Product ID: [%s]\nProduct name: %s\nProduct price: R$%,.2f\nProduct status: >%s<",
                this.id, this.productName, this.productPrice, this.status);
    }
}
