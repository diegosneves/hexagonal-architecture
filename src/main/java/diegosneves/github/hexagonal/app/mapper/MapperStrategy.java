package diegosneves.github.hexagonal.app.mapper;

public interface MapperStrategy <T, E> {

    T mapper(E origin);

}
