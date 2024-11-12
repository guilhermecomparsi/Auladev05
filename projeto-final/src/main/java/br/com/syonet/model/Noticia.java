package br.com.syonet.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Noticia extends PanacheEntity {

    @NotNull(message = "Título é obrigatório")
    @Size(min = 1, message = "Título não pode ser vazio")
    private String titulo;

    @NotNull(message = "Descrição é obrigatória")
    @Size(min = 1, message = "Descrição não pode ser vazia")
    private String descricao;

    private String link;
    private boolean processada;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isProcessada() {
        return processada;
    }

    public void setProcessada(boolean processada) {
        this.processada = processada;
    }
}
