package br.com.syonet;

import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.Mail;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class EmailService {

    @Inject
    Mailer mailer;  

    @Transactional
    public String enviarEmailDiario(List<Cliente> clientes, List<Noticia> noticias) {
        List<Noticia> noticiasNaoProcessadas = Noticia.find("processada", false).list();

        if (noticiasNaoProcessadas.isEmpty()) {
            System.out.println("Não há notícias não processadas para enviar.");
            return "Não há notícias não processadas para enviar.";
        }

        for (Cliente cliente : clientes) {
            StringBuilder corpoHtml = new StringBuilder("<html><body>");
            corpoHtml.append("<h2>Bom dia, ").append(cliente.getNome()).append("!</h2>");

            // Verificar se o cliente está fazendo aniversário
            if (cliente.getNascimento() != null &&
            cliente.getNascimento().getMonth() == LocalDate.now().getMonth() &&
            cliente.getNascimento().getDayOfMonth() == LocalDate.now().getDayOfMonth()) {
            corpoHtml.append("<p><strong>Feliz aniversário!</strong></p>");
        }
            System.out.println(LocalDate.now());
            System.out.println(cliente.getNascimento());

            corpoHtml.append("<p>Aqui estão as notícias do dia:</p>");
            corpoHtml.append("<ul>");

            // Adicionar as notícias não processadas
            for (Noticia noticia : noticiasNaoProcessadas) {
                corpoHtml.append("<li>");
                corpoHtml.append("<strong>").append(noticia.getTitulo()).append("</strong>");

                // Adicionar link da notícia, se existir
                if (noticia.getLink() != null) {
                    corpoHtml.append(" (<a href='").append(noticia.getLink()).append("'>link</a>)");
                }

                corpoHtml.append("<p>").append(noticia.getDescricao()).append("</p>");
                corpoHtml.append("</li>");
            }

            corpoHtml.append("</ul>");
            corpoHtml.append("<p>Atenciosamente,</p>");
            corpoHtml.append("<p><strong>Sua Empresa</strong></p>");
            corpoHtml.append("</body></html>");

            try {
                mailer.send(Mail.withHtml(cliente.getEmail(), "Notícias do dia!", corpoHtml.toString()));
                System.out.println("E-mail enviado para: " + cliente.getEmail());
            } catch (Exception e) {
                System.out.println("Erro ao enviar e-mail para " + cliente.getEmail() + ": " + e.getMessage());
            }

            // Marcar as notícias como processadas
            for (Noticia noticia : noticiasNaoProcessadas) {
                noticia.setProcessada(true);
                noticia.persist();
            }
        }

        return "E-mails enviados com sucesso!";
    }
}
