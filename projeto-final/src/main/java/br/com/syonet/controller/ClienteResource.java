package br.com.syonet.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.syonet.model.Cliente;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

        private List<Cliente> clientes = new ArrayList<>();


        @POST
        @Transactional  
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response criarCliente(@Valid Cliente cliente) {
            cliente.persist();
            clientes.add(cliente);
            return Response.status(Response.Status.CREATED).entity(cliente).build();
        }

        @GET
        public List<Cliente> listarClientes() {
            return Cliente.listAll();
        }
    }
