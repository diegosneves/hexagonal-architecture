package diegosneves.github.hexagonal.adapters.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A classe HexagonalArchitectureApplication é o ponto de entrada para o aplicativo de Arquitetura Hexagonal.
 *
 * <p>
 * Esta classe é anotada com @SpringBootApplication, indicando que é um aplicativo Spring Boot.
 * O método main é responsável por iniciar a aplicação através da chamada do método SpringApplication.run (),
 * passando a classe HexagonalArchitectureApplication e os argumentos da linha de comando como parâmetros.
 * </p>
 *
 * @see SpringBootApplication
 * @see SpringApplication#run(Class, String...)
 *
 * @author diegosneves
 */
@SpringBootApplication
public class HexagonalArchitectureApplication {

    public static void main(String[] args) {
        SpringApplication.run(HexagonalArchitectureApplication.class, args);
    }

}
