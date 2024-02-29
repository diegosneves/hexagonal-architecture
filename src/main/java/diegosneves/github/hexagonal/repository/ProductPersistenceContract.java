package diegosneves.github.hexagonal.repository;

/**
 * A interface {@link ProductPersistenceContract} é um contrato para as classes que manipulam operações de persistência para produtos.
 * Estende as interfaces {@link ProductReader} e {@link ProductWriter}.
 * <p>
 * Esta interface não contém nenhum método adicional, mas herda os métodos de suas interfaces parentes.
 * <p>
 * Esta interface atua como uma interface de marcador, fornecendo um tipo comum para classes que manipulam operações de leitura e escrita
 * relacionadas aos produtos.
 * <p>
 *
 * @see ProductReader
 * @see ProductWriter
 * @author diegoneves
 */
public interface ProductPersistenceContract extends ProductReader, ProductWriter {

}
