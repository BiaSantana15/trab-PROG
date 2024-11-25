
package view;

import controller.FuncionarioController;
import model.*;

import java.util.Scanner;

public class FuncionarioView {
    private final FuncionarioController controller = new FuncionarioController();
    private final Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        controller.carregarArquivo();
        int opcao;
        do {
            System.out.println("1. Adicionar Funcionário");
            System.out.println("2. Listar Funcionários");
            System.out.println("3. Atualizar Funcionário");
            System.out.println("4. Remover Funcionário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> adicionarFuncionario();
                case 2 -> controller.listarFuncionarios();
                case 3 -> atualizarFuncionario();
                case 4 -> removerFuncionario();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void adicionarFuncionario() {
        System.out.println("Escolha o tipo de funcionário: ");
        System.out.println("1. Desenvolvedor");
        System.out.println("2. Gerente");
        System.out.println("3. Treinador");
        System.out.println("4. Gerente Desenvolvedor");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Salário: ");
        double salario = scanner.nextDouble();

        Funcionario funcionario = switch (tipo) {
            case 1 -> new Desenvolvedor(nome, salario);
            case 2 -> new Gerente(nome, salario);
            case 3 -> new Treinador(nome, salario);
            case 4 -> new GerenteDesenvolvedor(nome, salario);
            default -> null;
        };

        if (funcionario != null) {
            controller.adicionarFuncionario(funcionario);
            System.out.println("Funcionário adicionado com sucesso!");
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private void atualizarFuncionario() {
        System.out.print("Nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.print("Novo salário: ");
        double novoSalario = scanner.nextDouble();
        controller.atualizarFuncionario(nome, novoSalario);
    }

    private void removerFuncionario() {
        System.out.print("Nome do funcionário: ");
        String nome = scanner.nextLine();
        controller.removerFuncionario(nome);
        System.out.println("Funcionário removido.");
    }
}
