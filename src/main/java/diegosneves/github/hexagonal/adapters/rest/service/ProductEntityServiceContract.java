package diegosneves.github.hexagonal.adapters.rest.service;

import diegosneves.github.hexagonal.adapters.rest.request.ProductRequest;
import diegosneves.github.hexagonal.adapters.rest.response.ProductResponse;
import diegosneves.github.hexagonal.app.exceptions.ProductException;

public interface ProductEntityServiceContract {

    ProductResponse get(String id) throws ProductException;

    ProductResponse create(ProductRequest request);

    ProductResponse enable(String id);

    ProductResponse disable(String id);

}
