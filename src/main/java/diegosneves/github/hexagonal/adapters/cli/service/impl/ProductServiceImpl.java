package diegosneves.github.hexagonal.adapters.cli.service.impl;

import diegosneves.github.hexagonal.adapters.cli.dto.ProductEntityDTO;
import diegosneves.github.hexagonal.adapters.cli.model.ProductEntity;
import diegosneves.github.hexagonal.adapters.cli.repository.ProductEntityRepository;
import diegosneves.github.hexagonal.adapters.cli.repository.impl.ProductEntityRepositoryImpl;
import diegosneves.github.hexagonal.adapters.cli.service.ProductEntityService;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.app.domain.product.factory.ProductFactory;
import diegosneves.github.hexagonal.app.exceptions.ProductException;
import diegosneves.github.hexagonal.app.exceptions.handler.ExceptionHandler;
import diegosneves.github.hexagonal.app.mapper.BuilderMapper;
import diegosneves.github.hexagonal.app.mapper.MapperStrategy;
import diegosneves.github.hexagonal.app.mapper.ProductEntityCLIMapper;

public class ProductServiceImpl implements ProductEntityService {

    private final ProductEntityRepository repository;

    public ProductServiceImpl() {
        this.repository = new ProductEntityRepositoryImpl();
    }

    @Override
    public ProductEntityDTO get(String id) throws ProductException {
        ProductContract productContract = this.repository.get(id.trim());
        if (productContract == null) {
            throw new ProductException(ExceptionHandler.PRODUCT_NOT_FOUND, id);
        }
        MapperStrategy<ProductEntity, ProductContract> mapperStrategy = new ProductEntityCLIMapper();
        ProductEntity productEntity = BuilderMapper.builderMapper(ProductEntity.class, productContract, mapperStrategy);

        return BuilderMapper.builderMapper(ProductEntityDTO.class, productEntity);
    }

    @Override
    public ProductEntityDTO create(String productName, Double productPrice) {
        ProductContract newProduct = ProductFactory.create(productName, productPrice);
        ProductContract createdProduct = this.repository.save(newProduct);
        MapperStrategy<ProductEntity, ProductContract> mapperStrategy = new ProductEntityCLIMapper();
        ProductEntity productEntity = mapperStrategy.mapper(createdProduct);

        return BuilderMapper.builderMapper(ProductEntityDTO.class, productEntity);
    }

    @Override
    public ProductEntityDTO enable(String id) {
        ProductContract productContract = this.repository.get(id.trim());
        if (productContract == null) {
            throw new ProductException(ExceptionHandler.PRODUCT_NOT_FOUND, id);
        }
        productContract.enable();
        ProductContract updatedProduct = this.repository.save(productContract);
        MapperStrategy<ProductEntity, ProductContract> mapperStrategy = new ProductEntityCLIMapper();
        ProductEntity productEntity = mapperStrategy.mapper(updatedProduct);
        return BuilderMapper.builderMapper(ProductEntityDTO.class, productEntity);
    }

    @Override
    public ProductEntityDTO disable(String id) {
        ProductContract productContract = this.repository.get(id.trim());
        if (productContract == null) {
            throw new ProductException(ExceptionHandler.PRODUCT_NOT_FOUND, id);
        }
        productContract.disable();
        ProductContract updatedProduct = this.repository.save(productContract);
        MapperStrategy<ProductEntity, ProductContract> mapperStrategy = new ProductEntityCLIMapper();
        ProductEntity productEntity = mapperStrategy.mapper(updatedProduct);
        return BuilderMapper.builderMapper(ProductEntityDTO.class, productEntity);
    }
}
