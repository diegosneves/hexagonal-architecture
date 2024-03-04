package diegosneves.github.hexagonal.app.mapper;

import diegosneves.github.hexagonal.app.exceptions.MapperFailureException;
import diegosneves.github.hexagonal.app.exceptions.handler.ExceptionHandler;

import java.lang.reflect.Field;

public interface BuilderMapper {

    /**
     * Mapeia os campos do objeto de origem para os campos da classe de destino.
     *
     * @param <T> o tipo da classe de destino
     * @param destinationClass a classe a ser mapeada
     * @param source o objeto de origem que será convertido no objeto de destino
     * @return uma instância da classe de destino com seus campos preenchidos
     * @throws MapperFailureException se ocorrer um erro ao mapear os campos
     */
    static <T> T builderMapper(Class<T> destinationClass, Object source) {

        var destinationFields = destinationClass.getDeclaredFields();

        T mappedInstance = null;

        try {
            mappedInstance = destinationClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new MapperFailureException(ExceptionHandler.CLASS_MAPPING_FAILURE, destinationClass.getName());
        }

        for(Field field : destinationFields) {
            field.setAccessible(true);
            try {
                var sourceField = source.getClass().getDeclaredField(field.getName());
                sourceField.setAccessible(true);
                field.set(mappedInstance, sourceField.get(source));
            } catch (Exception ignored) {
            }
        }

        return mappedInstance;
    }

    /**
     * Mapeia os campos do objeto de origem para os campos da classe de destino.
     *
     * @param <T> o tipo da classe de destino
     * @param <E> o tipo do objeto de origem
     * @param destinationClass a classe a ser mapeada
     * @param source o objeto de origem a ser convertido no objeto de destino
     * @param strategy a estratégia a ser usada para construir o objeto de destino (opcional)
     * @return uma instância da classe destino com seus campos preenchidos
     */
    static <T, E> T builderMapper(Class<T> destinationClass, E source, MapperStrategy<T, E> strategy) {
		if (strategy == null) {
			return BuilderMapper.builderMapper(destinationClass, source);
		}

		return strategy.mapper(source);
	}

}
