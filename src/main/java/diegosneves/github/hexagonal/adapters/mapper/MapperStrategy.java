package diegosneves.github.hexagonal.adapters.mapper;

public interface MapperStrategy <T, E> {

    T mapper(E origin);

}
