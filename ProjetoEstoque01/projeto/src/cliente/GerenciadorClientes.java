package cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorClientes {

    // modificador de acesso para privar a lista
    private List<Cliente> clientes = new ArrayList<>();

    // menu referente aos clientes
    public void menu(Scanner sc) {
        int op;
        do {
            System.out.println("\n--- CLIENTES ---");
            System.out.println("1 - Incluir Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    incluirCliente(sc);
                    break;
                case 2:
                    listarClientes();
                    break;
                case 0: 
                    {}
                    break;
                default: 
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (op != 0);
    }

    public void incluirCliente(Scanner sc) {
        String nome = null;

        // limpando o buffer
        sc.nextLine();
        System.out.printf("Tipo: 1 = Físico / 2 = Jurídico \nR:");
        int tipo = sc.nextInt();
        sc.nextLine();

        if (tipo == 1) {
            System.out.print("Nome: ");
            nome = sc.nextLine();

                // se o nome for vazio
                if (nome.isBlank()) {
                    System.out.println("Nome inválido.");
                    return;
                }

            System.out.print("CPF: ");
            String cpf = sc.nextLine();

            // busca pelo documento para não registrar duplicados 
            if (buscarPorDoc(cpf) != null) {
                System.out.println("Erro: CPF já cadastrado!");
                return;
            }

            // validação do cpf do cliente
            if (!Validacao.validacaoCPF(cpf)) {
                System.out.println("CPF inválido!");
                return;
            }

            // método try/catch para guardar os dados em clientes (array)
            try {
                if (nome != null && cpf != null) {
                    clientes.add(new ClienteFisico(nome, cpf));
                    System.out.println("\nCliente registrado com sucesso!!");
                }

            } catch (Exception e) {
                System.out.println("CPF inválido");
                return;
            }


        }
        else if (tipo == 2) 
        {
            System.out.print("Razão Social: ");
            nome = sc.nextLine();

                // se o nome for vazio
                if (nome.isBlank()) {
                    System.out.println("Nome inválido.");
                    return;
                }

            System.out.print("CNPJ: ");
            String cnpj = sc.nextLine();

            if (buscarPorDoc(cnpj) != null) {
                System.out.println("Erro: CNPJ já cadastrado!");
                return;
            }

            // validação do cnpj do cliente
            if (!Validacao.validacaoCNPJ(cnpj)) {
                System.out.println("CNPJ inválido!");
                return;
            }


            try {
                clientes.add(new ClienteJuridico(nome, cnpj));
                System.out.println("\nCliente registrado com sucesso!!");
            } catch (Exception e) {
                System.out.println("Erro no registro");
            }

        }
        else {
            System.out.println("Tipo inválido.");
        }
    }


    // listagem dos clientes com for 
    public void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Cliente c : clientes) {
            System.out.println(c.toString());
        }
    }

    // método de busca pelo documento 
    public Cliente buscarPorDoc(String doc) {
        for (Cliente c : clientes) {
            if (c.getIdentificacao().equals(doc)) {
                return c;
            }
        }
        return null;
    }
}
