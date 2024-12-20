package br.com.alura.LiterAlura.model;

public class Autor {

    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    public Autor() {
    }

    public Autor(Long id, String nome, Integer anoNascimento, Integer anoFalecimento) {
        this.id = id;
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoFalecimento = anoFalecimento;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", anoNascimento=" + anoNascimento +
                ", anoFalecimento=" + anoFalecimento +
                '}';
    }
}
