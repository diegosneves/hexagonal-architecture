package diegosneves.github.hexagonal.adapters.cli.service.impl;

import diegosneves.github.hexagonal.adapters.cli.dto.ProductEntityDTO;
import diegosneves.github.hexagonal.adapters.cli.service.ProductEntityService;
import diegosneves.github.hexagonal.app.exceptions.ProductException;

public class ProductServiceImpl implements ProductEntityService {

    @Override
    public ProductEntityDTO get(String id) throws ProductException {
        return null;
    }

    @Override
    public ProductEntityDTO create(String productName, Double productPrice) {
        return null;
    }

    @Override
    public ProductEntityDTO enable(String id) {
        return null;
    }

    @Override
    public ProductEntityDTO disable(String id) {
        return null;
    }
}
