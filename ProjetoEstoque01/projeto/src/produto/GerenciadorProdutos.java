


// pacote referente ao produto (organização do projeto por pastas)
package produto;

// importações necessárias
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GerenciadorProdutos {

    // lista que armazena todos os produtos cadastrados
    private List<Produto> produtos = new ArrayList<>();
    // ID automático que incrementa a cada novo produto
    private int proximoId = 1;

    // menu principal de gerenciamento de produtos
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

    //Cria objetos Produto, verifica duplicação e gera IDs automaticamente.
    public void incluirProduto(Scanner sc) {
        sc.nextLine(); // limpa buffer do Scanner
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        // verifica se já existe produto com o mesmo nome
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) { //comparar duas strings ignorando maiúsculas e minúsculas
                System.out.println("Erro: nome já cadastrado!");
                return;
            }
        }

        System.out.print("Quantidade inicial: ");
        int qtd = sc.nextInt();

        System.out.print("Preço: ");
        double preco = sc.nextDouble();

        // cria produto com ID automático
        Produto p = new Produto(proximoId++, nome, qtd, preco);

        produtos.add(p); // adiciona na lista

        System.out.println("Produto cadastrado!");
    }

    //Remove um produto da lista depois de verificar se ele existe.
    public void excluirProduto(Scanner sc) {
        System.out.print("ID para excluir: ");
        int id = sc.nextInt();

        Produto p = buscarPorId(id);

        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        // a regra de não excluir produtos vendidos é tratada no gerenciador de vendas
        produtos.remove(p);
        System.out.println("Produto removido.");
    }

    public Produto buscarPorId(int id) {
        // percorre a lista procurando o ID
        for (Produto p : produtos) {
            if (p.getId() == id) return p;
        }
        return null;
    }


    //Mostra todos os produtos cadastrados e indica quando estão sem estoque
    public void listarProdutos() {
        System.out.println("\n--- LISTA DE PRODUTOS ---");

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // mostra aviso quando o estoque é zero
        for (Produto p : produtos) {
            if (p.getQuantidade() == 0)
                System.out.println(p + " -> [SEM ESTOQUE]");
            else
                System.out.println(p);
        }
    }


    //Adiciona ou remove quantidade, impedindo que o estoque fique negativo.
    public void atualizarEstoque(Scanner sc) {
        System.out.print("ID do produto: ");
        int id = sc.nextInt();

        Produto p = buscarPorId(id);

        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade a adicionar/remover (use + ou -):\nR: ");
        int valor = sc.nextInt();

        // impede estoque negativo
        if (p.getQuantidade() + valor < 0) {
            System.out.println("Erro: estoque negativo.");
            return;
        }

        p.setQuantidade(p.getQuantidade() + valor);
        System.out.println("Estoque atualizado!");
    }


    //Busca tanto por ID quanto por nome ou parte do nome.
    public void buscarProduto(Scanner sc) {
        sc.nextLine(); // limpa buffer
        System.out.print("Nome, parte do nome ou ID: ");
        String termo = sc.nextLine();

        // primeiro tenta identificar se o termo é um número (ID)
        try {
            int id = Integer.parseInt(termo);
            Produto p = buscarPorId(id);

            if (p != null) {
                System.out.println("\nProduto encontrado por ID:");
                System.out.println(p);
            } else {
                System.out.println("Nenhum produto encontrado com esse ID.");
            }
            return; // encerra para não fazer busca pelo nome
        } catch (NumberFormatException e) {
            // não é número -> continua para busca por nome
        }

        boolean achou = false;

        System.out.println("\nResultado da busca:");
        for (Produto p : produtos) {
            // compara parte do nome ignorando maiúsculas/minúsculas
            if (p.getNome().toLowerCase().contains(termo.toLowerCase())) {
                System.out.println(p);
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Nenhum produto encontrado com esse nome.");
        }
    }


    //Soma o preço × quantidade de todos os produtos
    public void calcularValorTotalEstoque() {
        double total = 0;

        // valor total = soma de (quantidade × preço)
        for (Produto p : produtos) {
            total += p.getQuantidade() * p.getPreco();
        }

        System.out.printf("Valor total do estoque: R$ %.2f\n", total);
    }


    //Mostra produtos que estão abaixo de um limite informado
    public void relatorioEstoqueBaixo(Scanner sc) {
        System.out.print("Informe a quantidade de produtos para estoque baixo: ");
        int limite = sc.nextInt();

        boolean achou = false;

        System.out.println("\n--- PRODUTOS COM ESTOQUE BAIXO ---");

        for (Produto p : produtos) {
            if (p.getQuantidade() <= limite) {
                System.out.println(p);
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Nenhum produto com estoque igual ou inferior a " + limite + ".");
        }
    }

    // retorna a lista completa de produtos (útil para outras classes)
    public List<Produto> getProdutos() {
        return produtos;
    }

}
