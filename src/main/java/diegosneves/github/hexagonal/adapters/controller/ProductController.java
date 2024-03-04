package diegosneves.github.hexagonal.adapters.controller;

import diegosneves.github.hexagonal.adapters.request.ProductRequest;
import diegosneves.github.hexagonal.adapters.response.ProductResponse;
import diegosneves.github.hexagonal.adapters.service.ProductEntityServiceContract;
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

}