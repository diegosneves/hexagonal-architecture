package diegosneves.github.hexagonal.enums;

public enum ProductStatus {

    DISABLE,
    ENABLE;


    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
