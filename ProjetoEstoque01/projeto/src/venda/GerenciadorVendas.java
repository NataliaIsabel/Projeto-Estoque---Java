package venda;
// importação para manipulação da data
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Utilização de listas e arrays
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// importação dos pacotes 
import cliente.Cliente;
import cliente.GerenciadorClientes;
import produto.GerenciadorProdutos;
import produto.Produto;

// classe
public class GerenciadorVendas {

    private List<Venda> vendas = new ArrayList<>();
    private int proximoId = 1;

    private GerenciadorClientes gc;
    private GerenciadorProdutos gp;

    // construtor passando os gerenciadores para 'acesso' aos dados necessários
    public GerenciadorVendas(GerenciadorClientes gc, GerenciadorProdutos gp) {
        this.gc = gc;
        this.gp = gp;
    }

    // menu das vendas
    public void menu(Scanner sc) {
        int op;
        do {
            System.out.println("\n---- VENDAS ----");
            System.out.println("1 - Incluir Venda");
            System.out.println("2 - Listar Vendas");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    incluirVenda(sc);
                    break;
                case 2:
                    listarVendas();
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


    public void incluirVenda(Scanner sc) {

        // validação da data
        System.out.print("Data (dd/MM/yyyy): ");
         sc.nextLine();
        String dataString = sc.nextLine();

        if (!validarData(dataString)) {
            System.out.println("Data inválida! Use o formato dd/MM/yyyy.");
            return;
        }

        // convertendo para LocalDate para armazenar corretamente
        LocalDate dataVenda = LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // validacao do cliente
        System.out.print("CPF ou CNPJ do cliente: ");
        String doc = sc.nextLine();

        // a variavel doc é passada no parametro de buscaPorDoc() para que 
        // este seja localizado na classe de gerenciamento dos clientes
        Cliente cliente = gc.buscarPorDoc(doc);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        // validacao do produto
        System.out.print("ID do produto: ");
        int idProduto = sc.nextInt();

        // a variavel idProduto é passada no parametro de buscaPorId() para que 
        // este seja localizado na classe de gerenciamento dos produtos
        Produto produto = gp.buscarPorId(idProduto);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }


        // validação da quantidade
        System.out.print("Quantidade vendida: ");
        int qtd = sc.nextInt();

        if (qtd <= 0) {
            System.out.println("Quantidade deve ser maior que zero.");
            return;
        }

        if (produto.getQuantidade() < qtd) {
            System.out.println("Erro: estoque insuficiente.");
            return;
        }


        // registro da venda
        produto.setQuantidade(produto.getQuantidade() - qtd); // baixa estoque
        cliente.adicionarCompra(); // soma compra no cliente

        Venda venda = new Venda(proximoId++, dataVenda, cliente, produto, qtd);
        vendas.add(venda);

        System.out.println("Venda registrada com sucesso!");
    }

    public void listarVendas() {
        System.out.println("\n--- LISTA DE VENDAS ---");

        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        for (Venda v : vendas) {
            System.out.println(v.toString());
        }
    }

    // método para validar a data
    private boolean validarData(String data) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // 'tente' fazer a manipulação dos dados... Se não der certo 'retorne'
        // try Catch ~= if else 
        try {
            LocalDate.parse(data, formato);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
