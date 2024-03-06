package diegosneves.github.hexagonal.adapters.rest.service;

import diegosneves.github.hexagonal.adapters.rest.model.ProductEntity;
import diegosneves.github.hexagonal.adapters.rest.repository.ProductRepository;
import diegosneves.github.hexagonal.adapters.rest.request.ProductRequest;
import diegosneves.github.hexagonal.adapters.rest.response.ProductResponse;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.app.exceptions.ProductException;
import diegosneves.github.hexagonal.app.mapper.BuilderMapper;
import diegosneves.github.hexagonal.app.mapper.MapperStrategy;
import diegosneves.github.hexagonal.app.mapper.ProductEntityMapper;
import diegosneves.github.hexagonal.app.service.ProductService;
import diegosneves.github.hexagonal.app.service.ProductServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductEntityService implements ProductEntityServiceContract{


    private final ProductRepository productRepository;
    private final ProductServiceContract productServiceContract;

    @Autowired
    public ProductEntityService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productServiceContract = new ProductService(productRepository);
    }


    @Override
    public ProductResponse get(String id) throws ProductException {
        ProductContract productContract = this.productRepository.get(id.trim());
        MapperStrategy<ProductEntity, ProductContract> entityProductContractMapperStrategy = new ProductEntityMapper();
        ProductEntity productEntity = entityProductContractMapperStrategy.mapper(productContract);
        return BuilderMapper.builderMapper(ProductResponse.class, productEntity);
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        ProductContract createdProduct = this.productServiceContract.create(request.getProductName().trim(), request.getProductPrice());
        MapperStrategy<ProductEntity, ProductContract> entityProductContractMapperStrategy = new ProductEntityMapper();
        ProductEntity productEntity = entityProductContractMapperStrategy.mapper(this.productRepository.save(createdProduct));
        return BuilderMapper.builderMapper(ProductResponse.class, productEntity);
    }

    @Override
    public ProductResponse enable(String id) {
        ProductContract productContract = this.productRepository.get(id);
        productContract = this.productServiceContract.enable(productContract);
        MapperStrategy<ProductEntity, ProductContract> entityProductContractMapperStrategy = new ProductEntityMapper();
        ProductEntity productEntity = entityProductContractMapperStrategy.mapper(this.productRepository.save(productContract));
        return BuilderMapper.builderMapper(ProductResponse.class, productEntity);
    }

    @Override
    public ProductResponse disable(String id) {
        ProductContract productContract = this.productRepository.get(id);
        productContract = this.productServiceContract.disable(productContract);
        MapperStrategy<ProductEntity, ProductContract> entityProductContractMapperStrategy = new ProductEntityMapper();
        ProductEntity productEntity = entityProductContractMapperStrategy.mapper(this.productRepository.save(productContract));
        return BuilderMapper.builderMapper(ProductResponse.class, productEntity);
    }
}
