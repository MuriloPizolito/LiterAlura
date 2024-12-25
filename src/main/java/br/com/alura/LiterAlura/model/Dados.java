package br.com.alura.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties (ignoreUnknown = true)
public record Dados(
        @JsonAlias ("results") List<DadosLivro> results){

    @Override
    public String toString() {
        return "--------Livro--------" + "\n" +
                "Título: " + results().getFirst().titulo() + "\n" +
                "Autor: " + results().getFirst().autor().getFirst().nome() + "\n" +
                "Idioma: " + results().getFirst().idioma() + "\n" +
                "Número de downloads: " + results().getFirst().num_downloads() + "\n" +
                "----------------------";
    }
}
