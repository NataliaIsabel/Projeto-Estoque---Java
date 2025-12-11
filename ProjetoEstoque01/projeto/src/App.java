// importação das funcionalidades (receber dados)
import java.util.Scanner;

// importação dos pacotes / pastas criados
import produto.GerenciadorProdutos;
import cliente.GerenciadorClientes;
import venda.GerenciadorVendas;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Instanciando (iniciando) os gerenciadores
        GerenciadorProdutos gp = new GerenciadorProdutos();
        GerenciadorClientes gc = new GerenciadorClientes();
        GerenciadorVendas gv = new GerenciadorVendas(gc, gp);

        int opcao;

        do {
            System.out.println("\n- MENU PRINCIPAL -");
            System.out.println("1 - Produtos");
            System.out.println("2 - Clientes");
            System.out.println("3 - Vendas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    gp.menu(sc);
                    break;
                case 2:
                    gc.menu(sc);
                    break;
                case 3:
                    gv.menu(sc);
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);

        sc.close();
    }
}
