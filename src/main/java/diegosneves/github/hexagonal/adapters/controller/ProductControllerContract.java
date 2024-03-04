package diegosneves.github.hexagonal.adapters.controller;

import diegosneves.github.hexagonal.adapters.request.ProductRequest;
import diegosneves.github.hexagonal.adapters.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProductControllerContract {

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

}
