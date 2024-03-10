package diegosneves.github.hexagonal.adapters.rest.model;

import diegosneves.github.hexagonal.app.enums.ProductStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A classe {@link ProductEntity} representa uma entidade de {@link diegosneves.github.hexagonal.app.domain.product.entity.Product produto} no sistema.
 * Ela é anotada com @Entity para indicar que é uma entidade no banco de dados,
 * e @Table para especificar o nome da tabela no banco de dados.
 * Possui campos para o id do produto, nome, status e preço.
 * A classe também inclui construtores, getters, setters e um padrão de construtor (builder).
 *
 * @author diegosneves
 */
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
