package br.com.alura.LiterAlura.principal;


import br.com.alura.LiterAlura.model.Autor;
import br.com.alura.LiterAlura.model.Dados;
import br.com.alura.LiterAlura.model.DadosAutor;
import br.com.alura.LiterAlura.model.Livro;
import br.com.alura.LiterAlura.repository.LivroRepository;
import br.com.alura.LiterAlura.service.ConsumoApi;
import br.com.alura.LiterAlura.service.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private List<Dados> dadosList = new ArrayList<>();


    private LivroRepository livroRepository;

    public Principal(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }


    public void menuOperacoes() {
        var opcao = -1;

        while (opcao != 0) {

            System.out.println("""
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    0 - Sair
                    """);
            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroWeb();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    System.out.println("b");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }

    }

    // testes
    private void buscarLivro() {
        System.out.println("Digite o nome do livro para buscar: ");
        var busca = scanner.nextLine();
        var json = consumo.obterDados(ENDERECO + busca.replace(" ", "+"));
        Dados dados = conversor.obterDados(json, Dados.class);
        System.out.println(dados);
    }

    //     ideia de logica
    private void buscarLivroWeb() {
        Dados dados = getDadosWeb();
        Livro livro = new Livro();
        livro.setTitulo(dados.results().getFirst().titulo());
        livro.setIdioma(String.valueOf(dados.results().getFirst().idioma()));
        livro.setNum_download(dados.results().getFirst().num_downloads());
//        dadosList.add(dados);
        livroRepository.save(livro);
        System.out.println(dados);
    }

    private Dados getDadosWeb() {
        System.out.println("Digite o nome do livro para buscar: ");
        var busca = scanner.nextLine();
        var json = consumo.obterDados(ENDERECO + busca.replace(" ", "+"));
        Dados dados = conversor.obterDados(json, Dados.class);
        return dados;
    }

    private void listarLivros() {
        List<Livro> livros = livroRepository.findAll();
        livros.forEach(System.out::println);
    }




}
