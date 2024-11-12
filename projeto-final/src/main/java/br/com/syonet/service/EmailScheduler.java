package br.com.syonet.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.scheduler.Scheduled;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import br.com.syonet.model.Cliente;
import br.com.syonet.model.Noticia;

@ApplicationScoped
public class EmailScheduler {

    @Inject
    EmailService emailService;
    @Transactional
    @Scheduled(cron = "0 0 8 * * ?") 
    public void enviarEmailsDiarios() {
        System.out.println("Enviando o e-mail automático as" + LocalDate.now());
        List<Cliente> clientes = Cliente.listAll();
        List<Noticia> noticias = Noticia.listAll();

        emailService.enviarEmailDiario(clientes, noticias);

        String resultado = emailService.enviarEmailDiario(clientes, noticias);
        System.out.println(resultado);
    }
}

