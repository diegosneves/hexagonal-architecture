package diegosneves.github.hexagonal.adapters.rest.controller;

import diegosneves.github.hexagonal.adapters.rest.request.ProductRequest;
import diegosneves.github.hexagonal.adapters.rest.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * A interface {@link ProductControllerContract} provê um contrato para gerenciamento de operações relacionadas a produtos.
 * Define métodos para criar, recuperar, habilitar e desabilitar um produto.
 * <p>
 * Esta interface é parte do padrão de design da Arquitetura Hexagonal.
 *
 * @author diegosneves
 */
public interface ProductControllerContract {

    /**
     * Recebe os dados necessários para criar um produto e persisti-los no banco de dados.
     * Retorna as informações do novo produto.
     *
     * @param request a requisição do produto contendo o nome e preço do produto
     * @return a entidade de resposta contendo as informações do novo produto
     */
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Recebe os dados necessários para criar um produto e persistir na base de dados",
            tags = "Hexagonal Architecture - Product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informações do novo produto",
                    content = @Content)
    })
    ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request);

    /**
     * Recupera um produto pelo seu {@link java.util.UUID ID}.
     *
     * @param id o ID do produto a ser recuperado
     * @return o objeto ResponseEntity contendo as informações do produto recuperado
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Busca um produto pelo seu ID",
            tags = "Hexagonal Architecture - Product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informações do produto",
                    content = @Content)
    })
    ResponseEntity<ProductResponse> getById(@PathVariable("id") String id);

    /**
     * Ativa um produto pelo seu {@link java.util.UUID ID}.
     *
     * @param id o ID do produto a ser ativado
     * @return o objeto {@link ResponseEntity} contendo a informação do produto ativado
     */
    @PatchMapping(value = "enable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Ativa o produto por meio de seu ID",
            tags = "Hexagonal Architecture - Product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ativação do Produto",
                    content = @Content)
    })
    ResponseEntity<ProductResponse> enableProduct(@PathVariable("id") String id);

    /**
     * Desativa um produto pelo seu {@link java.util.UUID ID}.
     *
     * @param id o ID do produto a ser desativado
     * @return o objeto {@link ResponseEntity} contendo a informação do produto desativado
     */
    @PatchMapping(value = "disable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Desativa o produto por meio de seu ID",
            tags = "Hexagonal Architecture - Product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Desativação do Produto",
                    content = @Content)
    })
    ResponseEntity<ProductResponse> disableProduct(@PathVariable("id") String id);

}
