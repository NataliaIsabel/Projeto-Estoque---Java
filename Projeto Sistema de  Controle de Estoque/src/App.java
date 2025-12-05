import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// Classe principal executa o sistema de controle de estoque
// Contém o menu principal e integra os módulos de produtos, clientes e vendas

public class App {
    // Instâncias dos gerenciadores, variáveis de classe
    private static Clientes gerenciadorClientes;
    private static Produto gerenciadorProdutos;  
    private static Vendas gerenciadorVendas;
    private static Scanner scanner;

    public static void main(String[] args) throws Exception {
        // Inicializa os gerenciadores
        gerenciadorClientes = new Clientes();
        gerenciadorProdutos = new Produto(null, 0, 0); // Inicializa a classe Produto
        gerenciadorVendas = new Vendas();
        scanner = new Scanner(System.in);

        // Loop do menu principal
        while (true) {
            exibirMenuPrincipal(); // Adicionado para exibir o menu
            int opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1:
                    menuProdutos();
                    break;
                case 2:
                    menuClientes();
                    break;
                case 3:
                    menuVendas();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Exibe o menu principal
    public static void exibirMenuPrincipal() {
        System.out.println("\n---- Sistema de Controle de Estoque ----");
        System.out.println("1. Produtos");
        System.out.println("2. Clientes");
        System.out.println("3. Vendas");
        System.out.println("0. Sair");
    }

    // Menu para PRODUTOS
    private static void menuProdutos() {
        while (true) {
            System.out.println("\n ----PRODUTOS----");
            System.out.println("1. Incluir Produto");
            System.out.println("2. Excluir Produto");
            System.out.println("3. Listar Produtos");
            System.out.println("4. Atualizar Estoque");
            System.out.println("5. Buscar Produto");
            System.out.println("6. Calcular valor Total do Estoque");
            System.out.println("7. Relatório de Estoque Baixo");
            System.out.println("0. Voltar ao Menu Principal");
            int opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1:
                    incluirProduto();
                    break;
                case 2:
                    excluirProduto();
                    break;
                case 3:
                    Produto.listarProdutos(); 
                    break;
                case 4:
                    atualizarEstoque();
                    break;
                case 5:
                    buscarProduto();
                    break;
                case 6:
                    System.out.println("Valor total do estoque: R$ " + Produto.calcularValorTotalEstoque());
                    break;
                case 7:
                    relatorioEstoqueBaixo();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }






    // Menu para CLIENTES
    private static void menuClientes() {
        while (true) {
            System.out.println("\n ----CLIENTES----");
            System.out.println("1. Incluir Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("0. Voltar ao Menu Principal");
            int opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1:
                    incluirCliente();
                    break;
                case 2:
                    gerenciadorClientes.listarCliente();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }





    

    // Menu para VENDAS
    private static void menuVendas() {
        while (true) {
            System.out.println("\n ----VENDAS----");
            System.out.println("1. Incluir Vendas");
            System.out.println("2. Listar Vendas");
            System.out.println("0. Voltar ao Menu Principal");
            int opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1:
                    incluirVenda();
                    break;
                case 2:
                    gerenciadorVendas.listarVendas();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Métodos auxiliares

    // Incluir produto
    private static void incluirProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        int quantidade = lerInteiro("Quantidade inicial: ");
        double preco = lerDouble("Preço unitário: ");
        Produto.incluirProduto(nome, quantidade, preco); // Chama o método estático da classe Produto
    }

    // Excluir produto
    private static void excluirProduto() {
        int id = lerInteiro("ID do produto a excluir: ");
        Produto.excluirProduto(id); // Chama o método estático da classe Produto
    }

    // Atualizar estoque
    private static void atualizarEstoque() {
        int id = lerInteiro("ID do produto: ");
        int quantidade = lerInteiro("Quantidade a adicionar/remover (use negativo para remover): ");
        Produto.atualizarEstoque(id, quantidade); // Chama o método estático da classe Produto
    }

    // Buscar produto
    private static void buscarProduto() {
        System.out.print("Buscar por ID (digite número) ou nome (digite texto): ");
        String entrada = scanner.nextLine();
        Produto produto;

        // Verificar se a entrada é um número (ID) ou um texto (nome)
        if (isInteger(entrada)) {
            int id = Integer.parseInt(entrada);
            produto = Produto.buscarProdutoPorId(id); // Chama o método estático da classe Produto
        } else {
            produto = Produto.buscarProdutoPorNome(entrada); // Chama o método estático da classe Produto
        }

        // Exibir o resultado da busca
        if (produto != null) {
            System.out.println("Produto encontrado: " + produto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    // Relatório de estoque baixo
    private static void relatorioEstoqueBaixo() {
        int limite = lerInteiro("Digite o valor para estoque baixo: ");
        Produto.relatorioEstoqueBaixo(limite); // Chama o método estático da classe Produto
    }








    // Incluir cliente
    private static void incluirCliente() {
        System.out.print("Tipo de cliente (1 - Física, 2 - Jurídica): ");
        int tipo = lerInteiro("");
        System.out.print("Nome/Razão Social: ");
        String nome = scanner.nextLine();
        System.out.print("CPF/CNPJ: ");
        String identificacao = scanner.nextLine();

        // Declaração da variável cliente
        Cliente cliente;

        // Condicional para decidir o tipo de cliente
        if (tipo == 1) {
            cliente = new ClienteFisico(nome, identificacao);
        } else {
            cliente = new ClienteJuridico(nome, identificacao);
        }

        // Chamada para incluir o cliente
        gerenciadorClientes.incluirCliente(cliente);
    }









    // Incluir venda
    private static void incluirVenda() {
        System.out.print("Data da venda (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();
        System.out.print("CPF/CNPJ do cliente: ");
        String identificacaoCliente = scanner.nextLine();
        int idProduto = lerInteiro("ID do produto: ");
        int quantidade = lerInteiro("Quantidade vendida: ");
        gerenciadorVendas.incluirVenda(dataStr, identificacaoCliente, idProduto, quantidade, gerenciadorClientes, gerenciadorProdutos);
    }


    // Método para ler um número inteiro
    public static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextInt();
    }

    // Método para ler um número double
    public static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextDouble();
    }

    // Método para verificar se uma string é um número inteiro
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

