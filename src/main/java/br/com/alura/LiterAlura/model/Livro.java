package br.com.alura.LiterAlura.model;

public class Livro {

    private Long id;
    private Autor autor;
    private String titulo;
    private String idioma;
    private Integer num_dowload;

    public Livro() {
    }

    public Livro(Long id, Autor autor, String titulo, String idioma, Integer num_dowload) {
        this.id = id;
        this.autor = autor;
        this.titulo = titulo;
        this.idioma = idioma;
        this.num_dowload = num_dowload;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", autor=" + autor +
                ", titulo='" + titulo + '\'' +
                ", idioma='" + idioma + '\'' +
                ", num_dowload=" + num_dowload +
                '}';
    }
}
