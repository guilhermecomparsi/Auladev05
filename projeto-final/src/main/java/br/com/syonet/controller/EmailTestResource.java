package br.com.syonet.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import java.util.List;

import br.com.syonet.model.Cliente;
import br.com.syonet.model.Noticia;
import br.com.syonet.service.EmailService;

@Path("/testar-email")
public class EmailTestResource {

    @Inject
    EmailService emailService;

    @GET
    public Response testarEnvioDeEmail() {
        List<Cliente> clientes = Cliente.listAll();
        List<Noticia> noticias = Noticia.listAll();

        String status = emailService.enviarEmailDiario(clientes, noticias);

        return Response.ok(status).build();
    }
}