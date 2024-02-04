package diegosneves.github.hexagonal.enums;

/**
 * Enumerador que representa o status de um produto.
 * <p>
 * Esta classe enum define o status de um produto no sistema, que pode ser
 * HABILITADO (ENABLE) ou DESABILITADO (DISABLE). Cada status pode ser representado
 * como uma string em letras minúsculas.
 *
 * @author diegosneves
 */
public enum ProductStatus {

    /**
     * Representa o status de um produto.
     *
     * <p>
     * Esta variável é uma instância da enumeração {@link ProductStatus}, que define o
     * status de um produto no sistema. O status DISABLE indica que o produto está desabilitado.
     *
     * @see ProductStatus
     */
    DISABLE,
    /**
     * Representa o status de uma funcionalidade ou recurso.
     * <p>
     * A variável ENABLE é definida no enum {@link ProductStatus}, que é usado para representar
     * o status de um produto no sistema. O status ENABLE indica que o produto está habilitado.
     *
     * @see ProductStatus
     */
    ENABLE;


    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
