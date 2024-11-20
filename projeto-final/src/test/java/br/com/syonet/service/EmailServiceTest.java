package br.com.syonet.service;

import br.com.syonet.model.Cliente;
import br.com.syonet.model.Noticia;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class EmailServiceTest {

    @Inject
    EmailService emailService;

    @Test
    public void testEnviarEmailDiario() {
        // Criar clientes e notícias fictícias para o teste
        Cliente cliente = new Cliente();
        cliente.setNome("Guilherme");
        cliente.setEmail("guilherme@teste.com");
        cliente.persist();

        Noticia noticia = new Noticia();
        noticia.setTitulo("Notícia Importante");
        noticia.setDescricao("Descrição da notícia.");
        noticia.setProcessada(false);
        noticia.persist();

        String resultado = emailService.enviarEmailDiario(Cliente.listAll(), Noticia.find("processada", false).list());

        assertEquals("E-mails enviados com sucesso!", resultado);
    }
}
