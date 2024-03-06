package diegosneves.github.hexagonal.adapters.rest.response;

import diegosneves.github.hexagonal.app.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {

    private String id;
    private String productName;
    private ProductStatus status;
    private Double productPrice;

}
