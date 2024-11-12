package br.com.syonet;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import java.util.List;

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