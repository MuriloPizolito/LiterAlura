package br.com.alura.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias({"title"}) String titulo,
                         @JsonAlias({"authors"}) List<DadosAutor> autor,
                         @JsonAlias("languages") List<String>  idioma,
                         @JsonAlias("download_count") Integer num_dowloads) {
}
