package diegosneves.github.hexagonal.adapters.cli.model;

import diegosneves.github.hexagonal.adapters.cli.repository.Entity;
import diegosneves.github.hexagonal.app.enums.ProductStatus;

public class ProductEntity implements Entity<ProductEntity> {

    public static final String FIELD_SEPARATOR = ";";
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
    public String serialize() {
        return String.format("%s%s%s%s%s%s%s", this.id, FIELD_SEPARATOR, this.productName, FIELD_SEPARATOR, this.productPrice, FIELD_SEPARATOR, this.status);
    }

    @Override
    public ProductEntity deserialize(String... inputDateForDeserialization) {
        return new Builder()
                .id(inputDateForDeserialization[0])
                .productName(inputDateForDeserialization[1])
                .productPrice(Double.valueOf(inputDateForDeserialization[2]))
                .status(ProductStatus.valueOf(inputDateForDeserialization[3].toUpperCase()))
                .build();
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
