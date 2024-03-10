package diegosneves.github.hexagonal.adapters.cli.repository;

import diegosneves.github.hexagonal.app.repository.ProductPersistenceContract;
import diegosneves.github.hexagonal.app.repository.ProductReader;
import diegosneves.github.hexagonal.app.repository.ProductWriter;

/**
 * A interface {@link ProductEntityRepository} estende a interface {@link ProductPersistenceContract},
 * servindo como um contrato para classes que gerenciam operações de persistência para produtos.
 * Não define métodos adicionais, mas herda os métodos de suas interfaces parent.
 * <p>
 * Essa interface atua como uma interface de marcação, fornecendo um tipo comum para as classes que manipulam
 * operações de leitura e gravação relacionadas a produtos.
 *
 * @see ProductPersistenceContract
 * @see ProductReader
 * @see ProductWriter
 *
 * @author diegosneves
 */
public interface ProductEntityRepository extends ProductPersistenceContract {

}
