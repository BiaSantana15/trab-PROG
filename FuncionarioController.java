
package controller;

import model.Funcionario;

import java.io.*;
import java.util.ArrayList;

public class FuncionarioController {
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private final String arquivo = "funcionarios.txt";

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        salvarArquivo();
    }

    public void listarFuncionarios() {
        for (Funcionario f : funcionarios) {
            System.out.println(f.mostrarDetalhes());
        }
    }

    public void atualizarFuncionario(String nome, double novoSalario) {
        for (Funcionario f : funcionarios) {
            if (f.getNome().equalsIgnoreCase(nome)) {
                f.setSalario(novoSalario);
                salvarArquivo();
                return;
            }
        }
        System.out.println("Funcionário não encontrado!");
    }

    public void removerFuncionario(String nome) {
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
        salvarArquivo();
    }

    public void carregarArquivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            funcionarios = (ArrayList<Funcionario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nenhum dado carregado: " + e.getMessage());
        }
    }

    private void salvarArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(funcionarios);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
}
