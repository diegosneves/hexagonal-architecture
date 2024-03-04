package diegosneves.github.hexagonal.adapters.service;

import diegosneves.github.hexagonal.adapters.model.ProductEntity;
import diegosneves.github.hexagonal.adapters.repository.ProductRepository;
import diegosneves.github.hexagonal.adapters.request.ProductRequest;
import diegosneves.github.hexagonal.adapters.response.ProductResponse;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.app.exceptions.ProductException;
import diegosneves.github.hexagonal.adapters.mapper.BuilderMapper;
import diegosneves.github.hexagonal.adapters.mapper.MapperStrategy;
import diegosneves.github.hexagonal.adapters.mapper.ProductEntityMapper;
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
    public ProductResponse enable(ProductContract product) {// TODO - Verificar a melhor assinatura para esse metodo
        return null;
    }

    @Override
    public ProductResponse disable(ProductContract product) {// TODO - Verificar a melhor assinatura para esse metodo
        return null;
    }
}
