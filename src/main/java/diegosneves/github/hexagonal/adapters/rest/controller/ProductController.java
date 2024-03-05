package diegosneves.github.hexagonal.adapters.rest.controller;

import diegosneves.github.hexagonal.adapters.rest.request.ProductRequest;
import diegosneves.github.hexagonal.adapters.rest.response.ProductResponse;
import diegosneves.github.hexagonal.adapters.rest.service.ProductEntityServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adapter")
public class ProductController implements ProductControllerContract {

    private final ProductEntityServiceContract entityServiceContract;

    @Autowired
    public ProductController(ProductEntityServiceContract entityServiceContract) {
        this.entityServiceContract = entityServiceContract;
    }


    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductRequest request) {
        ProductResponse response = this.entityServiceContract.create(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ProductResponse> getById(String id) {
        ProductResponse response = this.entityServiceContract.get(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ProductResponse> enableProduct(String id) {
        ProductResponse response = this.entityServiceContract.enable(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ProductResponse> disableProduct(String id) {
        ProductResponse response = this.entityServiceContract.disable(id);
        return ResponseEntity.ok(response);
    }
}
