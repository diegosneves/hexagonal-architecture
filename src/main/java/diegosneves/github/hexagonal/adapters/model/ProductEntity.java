package diegosneves.github.hexagonal.adapters.model;

import diegosneves.github.hexagonal.enums.ProductStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductEntity {

    @Id
    private String id;
    private String productName;
    private ProductStatus status;
    private Double productPrice;

}
