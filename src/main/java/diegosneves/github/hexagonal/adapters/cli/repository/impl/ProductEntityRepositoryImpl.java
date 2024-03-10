package diegosneves.github.hexagonal.adapters.cli.repository.impl;

import diegosneves.github.hexagonal.adapters.cli.model.ProductEntity;
import diegosneves.github.hexagonal.adapters.cli.repository.ProductEntityRepository;
import diegosneves.github.hexagonal.app.domain.product.entity.Product;
import diegosneves.github.hexagonal.app.domain.product.entity.ProductContract;
import diegosneves.github.hexagonal.app.exceptions.ProductException;
import diegosneves.github.hexagonal.app.exceptions.handler.ExceptionHandler;
import diegosneves.github.hexagonal.app.mapper.MapperStrategy;
import diegosneves.github.hexagonal.app.mapper.ProductByCliEntityMapper;
import diegosneves.github.hexagonal.app.mapper.ProductEntityCLIMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * A classe {@link ProductEntityRepositoryImpl} implementa a interface {@link ProductEntityRepository} e fornece
 * métodos para gerenciar operações de persistência para produtos.
 * Esta classe realiza a leitura e a gravação de entidades de produto em um arquivo de texto.
 *
 * @author diegosneves
 */
public class ProductEntityRepositoryImpl implements ProductEntityRepository {

    private static final String FILE_PATH = "ProductEntityRepository.txt";

    @Override
    public ProductContract get(String id) {
        if (isNull(id) || id.trim().isBlank()) {
            throw new ProductException(ExceptionHandler.PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY);
        }
        MapperStrategy<Product, ProductEntity> mapperStrategy = new ProductByCliEntityMapper();
        ProductEntity productEntity = new ProductEntity();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] productData = line.split(ProductEntity.FIELD_SEPARATOR);
                if (productData[0].equals(id.trim())) {
                    productEntity = productEntity.deserialize(productData);
                    return mapperStrategy.mapper(productEntity);
                }
            }
        } catch (IOException e) {
            System.out.printf("Falha ao ler o arquivo: \n%s", e.getMessage());
        }
        return null;
    }

    @Override
    public ProductContract save(ProductContract product) {
        if (isNull(product)) {
            throw new ProductException(ExceptionHandler.PRODUCT_SHOULD_NOT_BE_NULL);
        }

        MapperStrategy<ProductEntity, ProductContract> mapperStrategy = new ProductEntityCLIMapper();
        List<ProductEntity> productList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ProductEntity entity = new ProductEntity().deserialize(line.split(ProductEntity.FIELD_SEPARATOR));
                productList.add(entity);
            }
            productList.removeIf(e -> e.getId().equals(product.getId()));
        } catch (IOException e) {
            System.out.printf("Falha ao ler o arquivo: \n%s", e.getMessage());
        }
        productList.add(mapperStrategy.mapper(product));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (ProductEntity entity : productList) {
                writer.write(entity.serialize());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.printf("Falha ao escrever no arquivo: \n%s", e.getMessage());
        }

        return product;
    }
}
