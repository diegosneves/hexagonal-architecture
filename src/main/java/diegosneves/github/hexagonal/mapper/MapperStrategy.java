package diegosneves.github.hexagonal.mapper;

public interface MapperStrategy <T, E> {

    T mapper(E origin);

}
