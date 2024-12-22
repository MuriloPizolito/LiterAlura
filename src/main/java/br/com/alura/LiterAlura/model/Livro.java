package br.com.alura.LiterAlura.model;

public class Livro {

    private Long id;
    private String titulo;
    private Autor autor;
    private String idioma;
    private Integer num_download;

    public Livro() {

    }

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        this.autor = (Autor) dadosLivro.autor();
        this.idioma = String.valueOf(dadosLivro.idioma());
        this.num_download = dadosLivro.num_downloads();
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", autor=" + autor +
                ", titulo='" + titulo + '\'' +
                ", idioma='" + idioma + '\'' +
                ", num_dowload=" + num_download +
                '}';
    }
}
