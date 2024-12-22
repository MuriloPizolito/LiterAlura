package br.com.alura.LiterAlura.principal;


import br.com.alura.LiterAlura.model.Dados;
import br.com.alura.LiterAlura.model.DadosLivro;
import br.com.alura.LiterAlura.model.Livro;
import br.com.alura.LiterAlura.service.ConsumoApi;
import br.com.alura.LiterAlura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

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
                    buscarLivro();
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

    private void buscarLivro() {
        System.out.println("Digite o nome do livro para buscar: ");
        var busca = scanner.nextLine();
        var json = consumo.obterDados(ENDERECO + busca.replace(" ", "+"));
//        var json = consumo.obterDados("https://gutendex.com/books/?search=dom+casmurro");
        System.out.println(json);
//        DadosLivro dadosLivro = conversor.obterDados(json, DadosLivro.class);
        Dados dados = conversor.obterDados(json, Dados.class);
//        System.out.println(dadosLivro);
        System.out.println(dados);

        System.out.println("--------Livro--------");
        System.out.println("Nome do livro: "+dados.results().getFirst().titulo());
        System.out.println("Autor: "+dados.results().getFirst().autor().getFirst().nome());
        System.out.println("Idioma: "+dados.results().getFirst().idioma());
        System.out.println("Número de downloads: "+dados.results().getFirst().num_downloads());
        System.out.println("----------------------");


    }

    private void listarLivros() {
        System.out.println("Livros registrados");
    }

    


}
