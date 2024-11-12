package br.com.syonet.controller;

import java.util.List;

import br.com.syonet.model.Noticia;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/noticias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoticiaResource {


   // Endpoint para criar uma notícia
   @POST
   @Transactional 
   public Response criarNoticia(@Valid Noticia noticia) {
       noticia.persist();  
       return Response.status(Response.Status.CREATED).entity(noticia).build();
   }

    @GET
    public List<Noticia> listarNoticias() {
        return Noticia.find("processada", false).list();
    }
}
