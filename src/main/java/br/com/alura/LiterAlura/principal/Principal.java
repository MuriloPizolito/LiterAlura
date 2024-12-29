package br.com.alura.LiterAlura.principal;


import br.com.alura.LiterAlura.model.Autor;
import br.com.alura.LiterAlura.model.Dados;
import br.com.alura.LiterAlura.model.DadosAutor;
import br.com.alura.LiterAlura.model.Livro;
import br.com.alura.LiterAlura.repository.AutorRepository;
import br.com.alura.LiterAlura.repository.LivroRepository;
import br.com.alura.LiterAlura.service.ConsumoApi;
import br.com.alura.LiterAlura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;


//    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
//        this.livroRepository = livroRepository;
//        this.autorRepository = autorRepository;
//    }
//
//    public Principal() {
//    }

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
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAno();
                    break;
                case 5:
                    listarAutoresPorIdioma();
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


    private void buscarLivroWeb() {
        Dados dados = getDadosWeb();
        Livro livro = new Livro();
        livro.setTitulo(dados.results().getFirst().titulo());
        livro.setIdioma(String.valueOf(dados.results().getFirst().idioma()));
        livro.setNum_download(dados.results().getFirst().num_downloads());

        List<Autor> autors = new ArrayList<>();
        Autor autor = new Autor();
        autor.setNome(dados.results().getFirst().autor().getFirst().nome());
        autor.setAnoFalecimento(dados.results().getFirst().autor().getFirst().anoFalecimento());
        autor.setAnoNascimento(dados.results().getFirst().autor().getFirst().anoNascimento());
        autors.add(autor);

        autorRepository.save(autor);
        livro.setAutores(autors);
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
        System.out.println("--------Livro--------");
        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutores().getFirst().getNome());
            System.out.println("Idioma: " + livro.getIdioma());
            System.out.println("Número de downloads: " + livro.getNum_download());
            System.out.println("----------------------");
        }

    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
        System.out.println("--------Autor--------");
        for (Autor autor : autores) {
            System.out.println("Autor: " + autor.getNome());
            System.out.println("Ano de nascimento: " + autor.getAnoNascimento());
            System.out.println("Ano de falecimento: " + autor.getAnoFalecimento());
            System.out.println("Livros: "+ autor.getLivros().getFirst().getTitulo());
            System.out.println("----------------------");
        }

    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Informe o ano que deseja pesquisar: ");
        var ano = scanner.nextInt();

        List<Autor> autorVivo = autorRepository.findByAnoFalecimentoGreaterThan(ano);
        autorVivo.forEach(System.out::println);

        System.out.println("--------Autor--------");
        for (Autor autor : autorVivo) {
            System.out.println("Autor: " + autor.getNome());
            System.out.println("Ano de nascimento: " + autor.getAnoNascimento());
            System.out.println("Ano de falecimento: " + autor.getAnoFalecimento());
            System.out.println("Livros: "+ autor.getLivros().getFirst().getTitulo());
            System.out.println("----------------------");
        }

    }

    private void listarAutoresPorIdioma() {
        System.out.println("Escolha o idioma em que deseja listar os livros:");
        System.out.println("es- espanhol");
        System.out.println("en- inglês");
        System.out.println("pt- português");
        var idioma = scanner.nextLine();

        if (idioma.equalsIgnoreCase("pt")) {
            idioma = "[pt]";
        } else if (idioma.equalsIgnoreCase("en")) {
            idioma = "[en]";
        } else if (idioma.equalsIgnoreCase("es")) {
            idioma = "[es]";
        } else {
            System.out.println("Língua não declarada");
            return;
        }

        List<Livro> livrosIdioma = livroRepository.findByIdiomaWithAutores(idioma);
//        livrosIdioma.forEach(System.out::println);
        System.out.println("--------Livro--------");
        for (Livro livro : livrosIdioma) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutores().getFirst().getNome());
            System.out.println("Idioma: " + livro.getIdioma());
            System.out.println("Número de downloads: " + livro.getNum_download());
            System.out.println("----------------------");
        }

    }


}
