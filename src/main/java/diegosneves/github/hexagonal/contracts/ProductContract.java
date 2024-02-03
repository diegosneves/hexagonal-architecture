package diegosneves.github.hexagonal.contracts;

import diegosneves.github.hexagonal.enums.ProductStatus;

public interface ProductContract {

    Boolean isValid();
    void enable();

    void disable();
    String getId();
    String getName();
    ProductStatus getStatus();
    Double getPrice();

}
