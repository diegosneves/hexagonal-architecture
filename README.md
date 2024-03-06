# Arquitetura Hexagonal

Conhecida também como **Ports and Adapters**, a Arquitetura Hexagonal é um padrão de design arquitetural focado em
desenvolver aplicações de maneira que não dependam de tecnologias específicas usadas em camadas de interface de usuário
ou persistência de dados. Esse padrão prioriza a separação de responsabilidades e organiza a aplicação em torno de um
domínio central.

No núcleo desta arquitetura encontram-se a lógica de negócio e os objetos de domínio. Interações com agentes externos,
como bancos de dados, interfaces da web, serviços de e-mail, entre outros, ocorrem através de abstrações denominadas
ports. Os adapters realizam a tarefa de converter os dados em formatos utilizáveis pelos ports e pelo domínio da
aplicação.

Essa estrutura permite que a aplicação seja mais mantida, testada e potencializa que quaisquer mudanças de tecnologias
na interface de usuário ou na persistência de dados afetem minimamente a lógica de negócio central.

---

## Swagger

- [Swagger - Local](http://localhost:8080/swagger-ui/index.html)
- [Api - Docs](http://localhost:8080/v3/api-docs)

---

#### API - Rest
```mermaid
classDiagram
direction BT
class ControllerExceptionHandler {
  + ControllerExceptionHandler() 
  + generalError(Exception) ResponseEntity~ExceptionDTO~
}
class CorsConfig {
  + CorsConfig() 
  + corsFilter() CorsFilter
}
class ExceptionDTO {
  + ExceptionDTO(String, int) 
  - String message
  - int statusCode
  + message() String
  + statusCode() int
}
class HexagonalArchitectureApplication {
  + HexagonalArchitectureApplication() 
  + main(String[]) void
}
class OpenApiConfig {
  + OpenApiConfig() 
  - getInfo() Info
  - getTags() List~Tag~
  + customOpenAPI() OpenAPI
}
class ProductController {
  + ProductController(ProductEntityServiceContract) 
  - ProductEntityServiceContract entityServiceContract
  + getById(String) ResponseEntity~ProductResponse~
  + createProduct(ProductRequest) ResponseEntity~ProductResponse~
  + enableProduct(String) ResponseEntity~ProductResponse~
  + disableProduct(String) ResponseEntity~ProductResponse~
}
class ProductControllerContract {
<<Interface>>
  + enableProduct(String) ResponseEntity~ProductResponse~
  + createProduct(ProductRequest) ResponseEntity~ProductResponse~
  + disableProduct(String) ResponseEntity~ProductResponse~
  + getById(String) ResponseEntity~ProductResponse~
}
class ProductEntity {
  + ProductEntity() 
  + ProductEntity(String, String, ProductStatus, Double) 
  - String id
  - String productName
  - ProductStatus status
  - Double productPrice
  + getId() String
  + getProductName() String
  + getStatus() ProductStatus
  + getProductPrice() Double
  + setId(String) void
  + setProductName(String) void
  + setStatus(ProductStatus) void
  + setProductPrice(Double) void
  + builder() ProductEntityBuilder
}
class ProductEntityService {
  + ProductEntityService(ProductRepository) 
  - ProductRepository productRepository
  - ProductServiceContract productServiceContract
  + get(String) ProductResponse
  + create(ProductRequest) ProductResponse
  + disable(String) ProductResponse
  + enable(String) ProductResponse
}
class ProductEntityServiceContract {
<<Interface>>
  + enable(String) ProductResponse
  + disable(String) ProductResponse
  + get(String) ProductResponse
  + create(ProductRequest) ProductResponse
}
class ProductRepository {
<<Interface>>
  + save(ProductContract) ProductContract
  + get(String) ProductContract
}
class ProductRequest {
  + ProductRequest(String, Double) 
  + ProductRequest() 
  - String productName
  - Double productPrice
  + getProductName() String
  + getProductPrice() Double
  + setProductName(String) void
  + setProductPrice(Double) void
  + builder() ProductRequestBuilder
}
class ProductResponse {
  + ProductResponse(String, String, ProductStatus, Double) 
  + ProductResponse() 
  - ProductStatus status
  - Double productPrice
  - String id
  - String productName
  + getId() String
  + getProductName() String
  + getStatus() ProductStatus
  + getProductPrice() Double
  + setId(String) void
  + setProductName(String) void
  + setStatus(ProductStatus) void
  + setProductPrice(Double) void
  + builder() ProductResponseBuilder
}
class WebSecurityConfig {
  + WebSecurityConfig() 
  + securityFilterChain(HttpSecurity) SecurityFilterChain
}

ControllerExceptionHandler  ..>  ExceptionDTO 
ProductController  ..>  ProductControllerContract 
ProductController "1" *--> "entityServiceContract 1" ProductEntityServiceContract 
ProductController  ..>  ProductRequest 
ProductController  ..>  ProductResponse 
ProductControllerContract  ..>  ProductRequest 
ProductControllerContract  ..>  ProductResponse 
ProductEntityService  ..>  ProductEntity 
ProductEntityService  ..>  ProductEntityServiceContract 
ProductEntityService "1" *--> "productRepository 1" ProductRepository 
ProductEntityService  ..>  ProductRequest 
ProductEntityService  ..>  ProductResponse 
ProductEntityServiceContract  ..>  ProductRequest 
ProductEntityServiceContract  ..>  ProductResponse 
ProductRepository  ..>  ProductEntity 

```

---
#### Domain

```mermaid
classDiagram
direction BT
class BuilderMapper {
<<Interface>>
  + builderMapper(Class~T~, E, MapperStrategy~T, E~) T
  + builderMapper(Class~T~, Object) T
}
class ExceptionHandler {
<<enumeration>>
  - ExceptionHandler(String) 
  +  PRODUCT_NOT_FOUND
  +  CLASS_MAPPING_FAILURE
  +  PRICE_LESS_THAN_ZERO
  +  PRODUCT_ID_SHOULD_NOT_BE_NULL_OR_EMPTY
  +  PRICE_EQUAL_OR_LESS_THAN_ZERO
  - String message
  +  PRODUCT_SHOULD_NOT_BE_NULL
  +  PRODUCT_NAME_SHOULD_NOT_BE_NULL_OR_EMPTY
  +  INVALID_UUID_FORMAT_MESSAGE
  +  PRODUCT_STATUS_SHOULD_NOT_BE_NULL
  + getMessage() String
  + getMessage(String) String
  + values() ExceptionHandler[]
  + valueOf(String) ExceptionHandler
}
class MapperFailureException {
  + MapperFailureException(ExceptionHandler, String) 
  + MapperFailureException(ExceptionHandler) 
}
class MapperStrategy~T, E~ {
<<Interface>>
  + mapper(E) T
}
class Product {
  + Product(String, String, Double) 
  - Double productPrice
  - String id
  - ProductStatus status
  - double ZERO
  - String productName
  + enable() void
  + getPrice() Double
  + getName() String
  + isValid() Boolean
  + getId() String
  + getStatus() ProductStatus
  + toString() String
  - isValidUUID() void
  + disable() void
}
class ProductContract {
<<Interface>>
  + isValid() Boolean
  + enable() void
  + getPrice() Double
  + getId() String
  + getName() String
  + getStatus() ProductStatus
  + disable() void
}
class ProductEntityMapper {
  + ProductEntityMapper() 
  + mapper(ProductContract) ProductEntity
}
class ProductException {
  + ProductException(ExceptionHandler) 
  + ProductException(ExceptionHandler, String) 
}
class ProductFactory {
  - ProductFactory() 
  + create(String, Double) Product
}
class ProductMapper {
  + ProductMapper() 
  - String STATUS_FIELD
  + mapper(ProductEntity) Product
}
class ProductPersistenceContract {
<<Interface>>

}
class ProductReader {
<<Interface>>
  + get(String) ProductContract
}
class ProductService {
  + ProductService(ProductPersistenceContract) 
  - ProductPersistenceContract persistence
  + get(String) ProductContract
  + enable(ProductContract) ProductContract
  + disable(ProductContract) ProductContract
  + create(String, Double) ProductContract
}
class ProductServiceContract {
<<Interface>>
  + enable(ProductContract) ProductContract
  + disable(ProductContract) ProductContract
  + get(String) ProductContract
  + create(String, Double) ProductContract
}
class ProductStatus {
<<enumeration>>
  + ProductStatus() 
  +  ENABLE
  +  DISABLE
  + valueOf(String) ProductStatus
  + toString() String
  + values() ProductStatus[]
}
class ProductWriter {
<<Interface>>
  + save(ProductContract) ProductContract
}

BuilderMapper  ..>  ExceptionHandler 
BuilderMapper  ..>  MapperFailureException 
BuilderMapper  ..>  MapperStrategy~T, E~ 
MapperFailureException  ..>  ExceptionHandler 
Product  ..>  ExceptionHandler 
Product  ..>  ProductContract 
Product  ..>  ProductException 
Product "1" *--> "status 1" ProductStatus 
ProductContract  ..>  Product 
ProductContract  ..>  ProductException 
ProductContract  ..>  ProductStatus 
ProductEntityMapper  ..>  MapperStrategy~T, E~ 
ProductEntityMapper  ..>  ProductContract 
ProductException  ..>  ExceptionHandler 
ProductFactory  ..>  Product 
ProductFactory  ..>  ProductStatus 
ProductMapper  ..>  MapperStrategy~T, E~ 
ProductMapper  ..>  Product 
ProductPersistenceContract  -->  ProductReader 
ProductPersistenceContract  -->  ProductWriter 
ProductReader  ..>  ProductContract 
ProductService  ..>  ExceptionHandler 
ProductService  ..>  Product 
ProductService  ..>  ProductContract 
ProductService  ..>  ProductException 
ProductService  ..>  ProductFactory 
ProductService "1" *--> "persistence 1" ProductPersistenceContract 
ProductService  ..>  ProductReader 
ProductService  ..>  ProductServiceContract 
ProductService  ..>  ProductWriter 
ProductServiceContract  ..>  ProductContract 
ProductServiceContract  ..>  ProductException 
ProductWriter  ..>  ProductContract 

```

---
#### CLI

```mermaid

```

---