package diegosneves.github.hexagonal.domain.product.entity;

import diegosneves.github.hexagonal.enums.ProductStatus;

public class Product implements ProductContract {

    private final String id;
    private String productName;
    private ProductStatus status;
    private Double productPrice;


    public Product(String id, String productName, ProductStatus status, Double productPrice) {
        this.id = id;
        this.productName = productName;
        this.status = status;
        this.productPrice = productPrice;
    }

    @Override
    public Boolean isValid() {
        return null;
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public ProductStatus getStatus() {
        return null;
    }

    @Override
    public Double getPrice() {
        return null;
    }
}
