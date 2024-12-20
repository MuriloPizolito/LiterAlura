package br.com.alura.LiterAlura.principal;


import br.com.alura.LiterAlura.model.Dados;
import br.com.alura.LiterAlura.model.DadosLivro;
import br.com.alura.LiterAlura.service.ConsumoApi;
import br.com.alura.LiterAlura.service.ConverteDados;

import java.util.Scanner;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    public void menuOpercoes() {

        System.out.println("Escolha uma opção: ");
        var opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                buscarLivro();
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }

    }

    private void buscarLivro() {
        System.out.println("Digite o nome do livro para buscar: ");
        var busca = scanner.nextLine();
//        var json = consumo.obterDados(ENDERECO + busca.replace(" ", "+"));
        var json = consumo.obterDados("https://gutendex.com/books/?search=dom+casmurro");
        System.out.println(json);
//        DadosLivro dadosLivro = conversor.obterDados(json, DadosLivro.class);
        Dados dados = conversor.obterDados(json, Dados.class);
        System.out.println(dados);
    }

}
