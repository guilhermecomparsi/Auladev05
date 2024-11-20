package br.com.syonet.controller;

import br.com.syonet.model.Noticia;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class NoticiaResourceTest {

    @Test
    public void testCriarNoticia() {
        Noticia noticia = new Noticia();
        noticia.setTitulo("Notícia de Teste");
        noticia.setDescricao("Descrição da notícia de teste");

        given()
            .contentType("application/json")
            .body(noticia)
            .when().post("/noticias")
            .then()
            .statusCode(Response.Status.CREATED.getStatusCode())
            .body("titulo", equalTo("Notícia de Teste"))
            .body("descricao", equalTo("Descrição da notícia de teste"));
    }

    @Test
    public void testListarNoticias() {
        given()
            .when().get("/noticias")
            .then()
            .statusCode(Response.Status.OK.getStatusCode())
            .body("$.size()", greaterThan(0));  // Verifica se há pelo menos uma notícia
    }
}
