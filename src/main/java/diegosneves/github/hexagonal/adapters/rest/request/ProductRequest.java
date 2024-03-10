package diegosneves.github.hexagonal.adapters.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A classe {@link ProductRequest} representa um objeto de requisição para a criação de um produto.
 * Ela contém o nome e o preço do produto.
 *
 * @author diegosneves
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {
    private String productName;
    private Double productPrice;
}
