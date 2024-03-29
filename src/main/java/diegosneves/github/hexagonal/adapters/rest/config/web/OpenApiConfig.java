package diegosneves.github.hexagonal.adapters.rest.config.web;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


/**
 * Classe de configuração para a documentação da API aberta ({@link OpenAPI}).
 * <p>
 * Esta classe configura as informações detalhadas e as tags que são exibidas na documentação do <b>Swagger/OpenAPI</b>.
 * <p>
 * Isso inclui meta-informações como a versão da API, título, descrição, detalhes de contato e tags usadas para agrupar endpoints relacionados.
 * <p>
 *
 * @author diegosneves
 */
@Configuration
public class OpenApiConfig {

    /**
     * Retorna uma instância personalizada do {@link OpenAPI}.
     * <p>
     * Este método configura informações detalhadas e tags que serão exibidas na documentação Swagger/OpenAPI.
     *
     * @return a instância personalizada do {@link OpenAPI}
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(getInfo())
                .tags(getTags());
    }


    /**
     * Busca informações sobre a API.
     *
     * @return Uma instância da classe {@link Info} contendo versão, título, descrição e detalhes de contato
     */
    private Info getInfo() {
        return new Info()
                .version("v0.0.1")
                .title("Hexagonal Architecture")
                .description("API representativa de um design Arquitetural")
                .contact(new Contact().email("neves.diegoalex@outlook.com").url("https://github.com/diegosneves/hexagonal-architecture").name("Diego Neves"));
    }

    /**
     * Recupera a lista de Etiquetas ({@link Tag Tags}).
     *
     * @return Uma lista de Etiquetas ({@link Tag Tags}), cada uma contendo um nome e uma descrição que detalham a
     * finalidade da respectiva Etiqueta.
     */
    private List<Tag> getTags() {
        return List.of(new Tag().name("Produto").description("Funcionalidades direcionadas para os Produtos"));
    }

}
