package br.unicesumar.prog1.manimentos;

import br.unicesumar.prog1.manimentos.modelo.Mantimentos;

import java.util.Scanner;

public class AplicacaoControleEstoque {

    private Mantimentos mantimentosList[] = new Mantimentos[5];
    private int posicaoAtual = 0;

    public static void main(String[] args) {

        AplicacaoControleEstoque app = new AplicacaoControleEstoque();
        app.tituloMenu();
        app.telaPrincipal();
    }

    private void telaPrincipal() {
        int opcao = 0;
        do {
            opcao = opcaoMenuPadrao();
            switch (opcao) {
                case 1:
                    menuCadastroMantimento();
                    break;
                case 2:
                    menuMovimentacao();
                    break;
                case 3:
                    relatorioDeMantimentos();
                    break;
                case 0:
                    System.out.println("Saindo do sistema");
                    break;
                default:
                    opcaoInvalida();
                    break;
            }
        } while (opcao != 0);
    }

    private void relatorioDeMantimentos() {
        this.tituloMenu();
        System.out.println("RELATÓRIO");
        for (int i = 0; i < posicaoAtual; i++) {
            System.out.println("\n");
            System.out.println("Mantimentos: \n" +
                    "CÓDIGO: " + i + "\n" +
                    mantimentosList[i]);

        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n");
        System.out.println("APERTE QUALQUER LETRA + ENTER PARA CONTINUAR");
        scanner.next();
    }


    private void menuMovimentacao() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ALTERAÇAO DOS MANTIMENTOS");
        System.out.println("1 - ENTRADA\n" +
                "2 - SAÍDA\n" +
                "0 - RETORNAR\n" +
                "OPÇÃO  : \n");
        int opcaoMovimentacao = scanner.nextInt();
        switch (opcaoMovimentacao) {
            case 1:
                compraMantimentos();
                break;
            case 2:
                saidaMovimentacao();
                break;
            case 0:
                System.out.println("Retornando para o menu");
                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    private void saidaMovimentacao() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("CONSUMO DOS MANTIMENTOS");
            System.out.println("Nome do mantimento");
            String nomeMantimento = scanner.nextLine();

            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (nomeMantimento.equalsIgnoreCase(mantimentosList[i].getNome())) {
                    controle=false;
                    Mantimentos mantimentosMovimentacao = mantimentosList[i];
                    System.out.println("QTDE ATUAL : " + mantimentosMovimentacao.getQuantidadeEmEstoque());
                    System.out.println("QTDE SAÍDA : ");
                    int quantidadeSaida = scanner.nextInt();
                    System.out.println("QTDE FINAL : " + (mantimentosMovimentacao.getQuantidadeEmEstoque() - quantidadeSaida));
                    if (mantimentosMovimentacao.getQuantidadeEmEstoque() < quantidadeSaida) {
                        System.out.println("Quantidade maior que no estoque, saída não é possível");
                        break;
                    }
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        mantimentosMovimentacao.setDiminuirQuantidade(quantidadeSaida);
                        mantimentosList[i] = mantimentosMovimentacao;
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private void compraMantimentos() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("COMPRA DE MANTIMENTOS");
            System.out.println("Nome do mantimento");
            String nomeMantimento = scanner.nextLine();
            Mantimentos mantimentosMovimentacao;
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (nomeMantimento.equalsIgnoreCase(mantimentosList[i].getNome())) {
                    controle=false;
                    mantimentosMovimentacao = mantimentosList[i];
                    System.out.println("QTDE ATUAL : " + mantimentosMovimentacao.getQuantidadeEmEstoque());
                    System.out.println("QTDE ENTRADA : ");
                    int quantidadeEntrada = scanner.nextInt();
                    System.out.println("QTDE FINAL : " + (mantimentosMovimentacao.getQuantidadeEmEstoque() + quantidadeEntrada));
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        mantimentosMovimentacao.setAdicionarQuantidade(quantidadeEntrada);
                        mantimentosList[i] = mantimentosMovimentacao;
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);

            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private void opcaoInvalida() {
        System.out.println("Opção inválida");
    }

    private int opcaoMenuPadrao() {
        int opcao;
        System.out.println("MENU PRINCIPAL\n" +
                "1 - CADASTRO DE MANTIMENTOS\n" +
                "2 - ALTERAÇÃO NOS MANTIMENTOS\n" +
                "3 - RELATÓRIOS\n" +
                "0 - FINALIZAR\n" +
                "OPÇÃO  : _\n");
        opcao = getEscolhaMenu();
        return opcao;
    }

    private void menuCadastroMantimento() {
        int opcao;
        System.out.println("1 - INCLUSÃO\n" +
                "2 - ALTERAÇÃO\n" +
                "3 - CONSULTA\n" +
                "4 - EXCLUSÃO\n" +
                "0 - RETORNAR\n");
        opcao = getEscolhaMenu();
        switch (opcao) {
            case 1:
                cadastrarMantimentos();
                break;
            case 2:
                alterarMantimento();
                break;
            case 3:
                consultarMantimento();
                break;
            case 4:
                excluirMantimento();
                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    private void excluirMantimento() {

        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            this.tituloMenu();
            System.out.println("EXCLUSÃO DE MANTIMENTOS");
            System.out.println("Informe o nome do mantimento para pesquisa");
            String nomeConsulta = scanner.nextLine();
            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++) {
                scanner = new Scanner(System.in);
                if (nomeConsulta.equalsIgnoreCase(mantimentosList[i].getNome())) {
                    controle=false;
                    System.out.println(mantimentosList[i].toString());
                    System.out.println("CONFIRMA EXCLUSÃO ( S/N ) ?");
                    escolha = scanner.next();
                    if (escolha.equalsIgnoreCase("S")) {
                        for (int j = i; j < posicaoAtual - 1; j++) {
                            mantimentosList[j] = mantimentosList[j + 1];
                            posicaoAtual--;
                        }
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private void consultarMantimento() {

        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            this.tituloMenu();
            System.out.println("CONSULTA DE MANTIMENTOS");
            System.out.println("Informe o nome do mantimento para pesquisa");
            String nomeConsulta = scanner.nextLine();
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (nomeConsulta.equalsIgnoreCase(mantimentosList[i].getNome())) {
                    controle=false;
                    System.out.println(mantimentosList[i].toString());
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private void alterarMantimento() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            this.tituloMenu();
            System.out.println("ALTERAÇÃO DE MANTIMENTO");
            System.out.println("Informe o nome do mantimento para alterar");
            String nomeConsulta = scanner.nextLine();
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {

                if (nomeConsulta.equalsIgnoreCase(mantimentosList[i].getNome())) {
                    controle=false;
                    System.out.println("MANTIMENTO ENCONTRADO\n");
                    Mantimentos mantimentos = setDadosDoMantimento();
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        mantimentosList[i] = mantimentos;
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private void mensagemConsultaNaoEncontrada(boolean controle) {
        if (controle) {
            System.out.println("Mantimentos não encontrado");
        }
    }

    private void cadastrarMantimentos() {
        String escolha;
        do {
            this.tituloMenu();
            System.out.println("CADASTRO DE MANTIMENTOS");
            Mantimentos mantimentos = setDadosDoMantimento();
            escolha = confirmaOperacao();
            if (escolha.equalsIgnoreCase("S")) {
                mantimentosList[posicaoAtual] = mantimentos;
                posicaoAtual++;
            }
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private Mantimentos setDadosDoMantimento() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o nome do mantimentos");
        String nome = scanner.nextLine();
        System.out.println("Informe a unidade de medida");
        scanner = new Scanner(System.in);
        String unidade = scanner.nextLine();
        System.out.println("Informe a quantidade");
        int quantidade = scanner.nextInt();

        Mantimentos mantimentos = new Mantimentos();
        mantimentos.setNome(nome);
        mantimentos.setUnidade(unidade);
        mantimentos.setQuantidadeEmEstoque(quantidade);
        return mantimentos;
    }

    private String getRepetirOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("REPETIR OPERAÇÃO ( S/N ) ? ");
        escolha = scanner.next();
        return escolha;
    }

    private String confirmaOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("CONFIRMA OPERAÇÃO ( S/N ) ?");
        escolha = scanner.next();
        return escolha;
    }

    private int getEscolhaMenu() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.next());
    }

    private void tituloMenu() {
        System.out.println("CONTROLE DE MANTIMENTOS PARA APOCALIPSE ZUMBI.\n");
    }
}
