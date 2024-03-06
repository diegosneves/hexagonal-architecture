package diegosneves.github.hexagonal.adapters.cli.service;

import diegosneves.github.hexagonal.adapters.cli.dto.ProductEntityDTO;
import diegosneves.github.hexagonal.app.exceptions.ProductException;

public interface ProductEntityService {

    ProductEntityDTO get(String id) throws ProductException;

    ProductEntityDTO create(String productName, Double productPrice);

    ProductEntityDTO enable(String id);

    ProductEntityDTO disable(String id);

}
