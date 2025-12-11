// pacote referente ao produto, ou seja, os pacotes são as separações do projeto por pastas.
package produto;

// importações para manipulação das listas e leitura de dados
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GerenciadorProdutos {

    // lista de produtos
    private List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    // menu das opções do produto
    public void menu(Scanner sc) {
        int op;
        do {
            System.out.println("\n-- PRODUTOS --");
            System.out.println("1 - Incluir Produto");
            System.out.println("2 - Excluir Produto");
            System.out.println("3 - Listar Produtos");
            System.out.println("4 - Atualizar Estoque");
            System.out.println("5 - Buscar Produto");
            System.out.println("6 - Valor total do estoque");
            System.out.println("7 - Relatório de Estoque baixo");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt();

            switch (op) {
                case 1: 
                    incluirProduto(sc);
                    break;
                case 2: 
                    excluirProduto(sc);
                    break;
                case 3: 
                    listarProdutos();
                    break;
                case 4: 
                    atualizarEstoque(sc);
                    break;
                case 5: 
                    buscarProduto(sc);
                    break;
                case 6: 
                    calcularValorTotalEstoque();
                    break;
                case 7: 
                    relatorioEstoqueBaixo(sc);
                    break;
                case 0: 
                    System.out.println("Voltando ao menu principal...");
                    break;
                default: 
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (op != 0);
    }

    public void incluirProduto(Scanner sc) {
        sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        // verificar duplicação
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Erro: nome já cadastrado!");
                return;
            }
        }

        System.out.print("Quantidade inicial: ");
        int qtd = sc.nextInt();

        System.out.print("Preço: ");
        double preco = sc.nextDouble();

        // utilização do construtor
        Produto p = new Produto(proximoId++, nome, qtd, preco);

        // adicionar à lista
        produtos.add(p);

        System.out.println("Produto cadastrado!");
    }

    public void excluirProduto(Scanner sc) {
        System.out.print("ID para excluir: ");
        int id = sc.nextInt();

        Produto p = buscarPorId(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        // regra: não excluir se já foi vendido -> validado no gerenciador de vendas
        produtos.remove(p);
        System.out.println("Produto removido.");
    }

    public Produto buscarPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public void listarProdutos() {
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto p : produtos) {
            if (p.getQuantidade() == 0)
                System.out.println(p + " -> [SEM ESTOQUE]");
            else
                System.out.println(p);
        }
    }

    public void atualizarEstoque(Scanner sc) {
        System.out.print("ID do produto: ");
        int id = sc.nextInt();
        Produto p = buscarPorId(id);

        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade a adicionar/remover (colocar na frente: (+/-))\nR: ");
        int valor = sc.nextInt();

        if (p.getQuantidade() + valor < 0) {
            System.out.println("Erro: estoque negativo.");
            return;
        }

        p.setQuantidade(p.getQuantidade() + valor);
        System.out.println("Estoque atualizado!");
    }

    public void buscarProduto(Scanner sc) {
        sc.nextLine();
        System.out.print("Nome, parte do nome ou ID: ");
        String termo = sc.nextLine();

        // Tentativa de busca por ID
        try {
            int id = Integer.parseInt(termo);   // se for número, entra aqui
            Produto p = buscarPorId(id);

            if (p != null) {
                System.out.println("\nProduto encontrado por ID:");
                System.out.println(p);
            } else {
                System.out.println("Nenhum produto encontrado com esse ID.");
            }
            return; // retorna antes de buscar o número
        } catch (NumberFormatException e) {
            // vai passar para busca por nome ou parte dele
        }

        // Busca por nome ou parte do nome
        boolean achou = false;

        System.out.println("\nResultado da busca:");
        for (Produto p : produtos) {
            if (p.getNome().toLowerCase().contains(termo.toLowerCase())) {
                System.out.println(p);
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Nenhum produto encontrado com esse nome.");
        }
    }

    public void calcularValorTotalEstoque() {
        double total = 0;

        for (Produto p : produtos) {
            total += p.getQuantidade() * p.getPreco();
        }

        System.out.printf("Valor total do estoque: R$ %.2f\n", total);
    }

    public void relatorioEstoqueBaixo(Scanner sc) {
    System.out.print("Informe a quantidade de produtos para estoque baixo: ");
    int limite = sc.nextInt();

    // define como não encontrado, 
    // mas tem manipulação caso encontre os dados para tornar 'true' 
    // (que seria quando encontra os produtos no array)
    boolean achou = false;

    System.out.println("\n--- PRODUTOS COM ESTOQUE BAIXO ---");

    for (Produto p : produtos) {
        if (p.getQuantidade() <= limite) {
            System.out.println(p);
            achou = true;
        }
    }

    // se não encontrou 
    if (!achou) {
        System.out.println("Nenhum produto com estoque igual ou inferior a " + limite + ".");
    }
}



    // método get para retornar a lista de produtos
    public List<Produto> getProdutos() {
        return produtos;
    }



}
