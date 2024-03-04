package diegosneves.github.hexagonal.adapters.service;

import diegosneves.github.hexagonal.adapters.request.ProductRequest;
import diegosneves.github.hexagonal.adapters.response.ProductResponse;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.app.exceptions.ProductException;

public interface ProductEntityServiceContract {

    ProductResponse get(String id) throws ProductException;

    ProductResponse create(ProductRequest request);

    ProductResponse enable(ProductContract product);

    ProductResponse disable(ProductContract product);

}
