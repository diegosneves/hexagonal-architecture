package diegosneves.github.hexagonal.app.mapper;

/**
 * A interface {@link MapperStrategy} é utilizada para definir uma estratégia de mapeamento entre um objeto de origem do tipo E e um objeto de destino do tipo T.
 *
 * @param <T> O tipo do objeto de destino.
 * @param <E> O tipo do objeto de origem.
 *
 * @author diegosneves
 */
public interface MapperStrategy <T, E> {

    /**
     * Mapeia um objeto do tipo E para um objeto do tipo T.
     *
     * @param origin O objeto a ser mapeado.
     * @return O objeto mapeado.
     */
    T mapper(E origin);

}
