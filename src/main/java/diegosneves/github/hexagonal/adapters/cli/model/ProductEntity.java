package diegosneves.github.hexagonal.adapters.cli.model;

import diegosneves.github.hexagonal.app.enums.ProductStatus;

import java.io.Serializable;

public class ProductEntity implements Serializable {

    private String id;
    private String productName;
    private ProductStatus status;
    private Double productPrice;

    public ProductEntity() {
    }

    public ProductEntity(String id, String productName, ProductStatus status, Double productPrice) {
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

    public static class Builder {
        private String id;
        private String productName;
        private ProductStatus status;
        private Double productPrice;

        public Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder productPrice(Double productPrice) {
            this.productPrice = productPrice;
            return this;
        }

        public Builder status(ProductStatus status) {
            this.status = status;
            return this;
        }

        public ProductEntity build() {
            return new ProductEntity(this.id, this.productName, this.status, this.productPrice);
        }
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id='" + this.id + '\'' +
                ", productName='" + this.productName + '\'' +
                ", status=" + this.status +
                ", productPrice=" + this.productPrice +
                '}';
    }
}
