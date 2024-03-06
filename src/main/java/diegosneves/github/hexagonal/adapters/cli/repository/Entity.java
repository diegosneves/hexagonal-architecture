package diegosneves.github.hexagonal.adapters.cli.repository;

import java.io.Serializable;

public interface Entity <T> extends Serializable {

    String serialize();

    T deserialize(String... inputDateForDeserialization);

}
