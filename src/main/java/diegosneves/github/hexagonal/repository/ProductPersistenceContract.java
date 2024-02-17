package diegosneves.github.hexagonal.repository;

import org.springframework.stereotype.Repository;

/**
 * A interface {@link ProductPersistenceContract} é um contrato para as classes que manipulam operações de persistência para produtos.
 * Estende as interfaces {@link ProductReader} e {@link ProductWriter}.
 * <p>
 * Esta interface não contém nenhum método adicional, mas herda os métodos de suas interfaces parentes.
 * <p>
 * Esta interface atua como uma interface de marcador, fornecendo um tipo comum para classes que manipulam operações de leitura e escrita
 * relacionadas aos produtos.
 * <p>
 * A classe que implementa esta interface deve ser anotada com a anotação {@link Repository}.
 * Isso garante que ela seja automaticamente registrada como um bean para ser usado por outros componentes.
 *
 * @see ProductReader
 * @see ProductWriter
 * @author diegoneves
 */
@Repository
public interface ProductPersistenceContract extends ProductReader, ProductWriter {

}
