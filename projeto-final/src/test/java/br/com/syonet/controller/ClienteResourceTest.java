package br.com.syonet.controller;

import br.com.syonet.model.Cliente;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ClienteResourceTest {

    @Test
    public void testCriarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Guilherme");
        cliente.setEmail("guilherme@teste.com");

        given()
            .contentType("application/json")
            .body(cliente)
            .when().post("/clientes")
            .then()
            .statusCode(Response.Status.CREATED.getStatusCode())
            .body("nome", equalTo("Guilherme"))
            .body("email", equalTo("guilherme@teste.com"));
    }

    @Test
    public void testListarClientes() {
        given()
            .when().get("/clientes")
            .then()
            .statusCode(Response.Status.OK.getStatusCode())
            .body("$.size()", greaterThan(0));  // Verifica se há pelo menos um cliente
    }
}
    