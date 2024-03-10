# Arquitetura Hexagonal 
[![CI Develop](https://github.com/diegosneves/hexagonal-architecture/actions/workflows/ci-develop.yaml/badge.svg)](https://github.com/diegosneves/hexagonal-architecture/actions/workflows/ci-develop.yaml)
[![wakatime](https://wakatime.com/badge/user/018bea20-dbbc-48e2-b101-5415903acf5a/project/018d6c68-6a0d-455d-b172-3016d1867673.svg)](https://wakatime.com/@diegosneves)

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
# Como Configurar e Executar o Projeto na Sua IDE

Este projeto utiliza o padrão `REST` com Spring e `Java puro`. Aqui estão as etapas para configurá-lo e executá-lo na sua IDE:

## Pré-requisitos

1. **Java:** Este projeto é baseado em Java, então, você precisará ter ele instalado no seu sistema.

2. **IDE compatível:** Você vai precisar de uma IDE compatível com Java e Spring, como o IntelliJ IDEA ou o Eclipse.

## Configuração do Projeto

1. Clone o projeto do repositório GitHub para a sua máquina local usando o seguinte comando no terminal:

    ```shell
    git clone git@github.com:diegosneves/hexagonal-architecture.git
    ```

2. Abra a sua IDE de escolha e importe o projeto clonado. Geralmente, isso pode ser realizado selecionando `File -> Open` e navegando até o diretório do projeto.

3. Certifique-se de que sua IDE reconheceu corretamente o projeto como um projeto `Maven` e que todas as dependências necessárias foram baixadas corretamente.

## Execução do Projeto com Java puro

O projeto utiliza Spring Boot, o que simplifica a sua execução. No pacote `CLI`, você irá encontrar o ponto de entrada para o projeto. Aqui estão as etapas para rodá-lo:

1. Navegue até o diretório `src/main/java/diegosneves/github/hexagonal/adapters/` no pacote `CLI`.

2. Procure pela classe `Application.java` com o método `main()`. Esta é a classe principal que inicia a aplicação.

3. Clique com o botão direito na classe e selecione a opção `Run` para executar o projeto.

#### CLI

```mermaid
classDiagram
direction BT
class Application {
  + Application() 
  + main(String[]) void
}
class Entity~T~ {
<<Interface>>
  + serialize() String
  + deserialize(String[]) T
}
class Menu {
  + Menu() 
  - ProductEntityService productEntityService
  - String MENU_DIVIDER
  - String NEW_LINE
  - String SINGLE_OPTION
  - String MENU_MESSAGE
  - String MENU_OPTION_SEPARATOR
  - int MENU_DIVIDER_SIZE
  - String TOTAL_OPTIONS
  + displayMenu() void
  - optionBuilder(Scanner, String[]) int
}
class ProductEntity {
  + ProductEntity(String, String, ProductStatus, Double) 
  + ProductEntity() 
  - String id
  + String FIELD_SEPARATOR
  - ProductStatus status
  - String productName
  - Double productPrice
  + serialize() String
  + deserialize(String[]) ProductEntity
  + toString() String
  + getProductPrice() Double
  + getStatus() ProductStatus
  + getId() String
  + getProductName() String
}
class ProductEntityDTO {
  + ProductEntityDTO(String, String, ProductStatus, Double) 
  + ProductEntityDTO() 
  - String id
  - String productName
  - Double productPrice
  - ProductStatus status
  + getStatus() ProductStatus
  + getId() String
  + getProductPrice() Double
  + getProductName() String
  + toString() String
}
class ProductEntityRepository {
<<Interface>>

}
class ProductEntityRepositoryImpl {
  + ProductEntityRepositoryImpl() 
  - String FILE_PATH
  + save(ProductContract) ProductContract
  + get(String) ProductContract
}
class ProductEntityService {
<<Interface>>
  + get(String) ProductEntityDTO
  + enable(String) ProductEntityDTO
  + create(String, Double) ProductEntityDTO
  + disable(String) ProductEntityDTO
}
class ProductServiceImpl {
  + ProductServiceImpl() 
  - ProductEntityRepository repository
  + disable(String) ProductEntityDTO
  + create(String, Double) ProductEntityDTO
  + get(String) ProductEntityDTO
  + enable(String) ProductEntityDTO
}
class QuestionHandler {
<<enumeration>>
  + QuestionHandler() 
  +  INTEGER
  +  DOUBLE
  - String QUESTION_FORMAT
  +  STRING
  + response(Scanner, String) Object
}

Application  ..>  Menu 
Menu  ..>  ProductEntityDTO 
Menu "1" *--> "productEntityService 1" ProductEntityService 
Menu  ..>  ProductServiceImpl 
Menu  ..>  QuestionHandler 
ProductEntity  ..>  Entity~T~ 
ProductEntityRepositoryImpl  ..>  ProductEntity 
ProductEntityRepositoryImpl  ..>  ProductEntityRepository 
ProductEntityService  ..>  ProductEntityDTO 
ProductServiceImpl  ..>  ProductEntity 
ProductServiceImpl  ..>  ProductEntityDTO 
ProductServiceImpl "1" *--> "repository 1" ProductEntityRepository 
ProductServiceImpl  ..>  ProductEntityRepositoryImpl 
ProductServiceImpl  ..>  ProductEntityService 

```

---

## Utilização da API REST

O projeto utiliza Spring Boot, o que simplifica a sua execução. Com o servidor agora em execução, você será capaz de interagir com a API REST por meio de protocolo HTTP. As operações de CRUD (Criar, Ler, Atualizar, Deletar) são geralmente mapeadas para os métodos HTTP POST, GET, PUT e DELETE respectivamente. 

Utilize o [swagger](#swagger), para enviar solicitações à API e receber respostas.

Esperamos que estas instruções auxiliem você a iniciar e configurar este projeto na sua IDE. Caso encontre qualquer problema, sinta-se à vontade para abrir uma "issue" no projeto no GitHub.

## Swagger

- [Swagger - Local](http://localhost:8080/swagger-ui/index.html)
- [Api - Docs](http://localhost:8080/v3/api-docs)


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
  + toString() String
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