package diegosneves.github.hexagonal.adapters.rest.response;

import diegosneves.github.hexagonal.app.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa um objeto de resposta que contém informações sobre um produto.
 *
 * @author diegosneves
 */
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
