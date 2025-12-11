



import java.util.Scanner; 
// importa a classe Scanner que permite ler dados digitados

// importação dos pacotes / pastas criados
import produto.GerenciadorProdutos; 
import cliente.GerenciadorClientes;  
import venda.GerenciadorVendas;       

//classe publica
public class App { 
    public static void main(String[] args) { 

        Scanner sc = new Scanner(System.in); 
        // Cria um objeto Scanner chamado 'sc' para ler entradas do teclado (System.in).

        // Instanciando (iniciando) os gerenciadores
        // Esses objetos vão controlar diferentes partes do sistema (produtos, clientes e vendas).
        GerenciadorProdutos gp = new GerenciadorProdutos(); 
        GerenciadorClientes gc = new GerenciadorClientes(); 
        GerenciadorVendas gv = new GerenciadorVendas(gc, gp); 

        int opcao; 
        // Declara a variável que armazenará a opção digitada pelo usuário no menu principal.

        do { 
            System.out.println("\n- MENU PRINCIPAL -"); 
            System.out.println("1 - Produtos"); 
            System.out.println("2 - Clientes");
            System.out.println("3 - Vendas"); 
            System.out.println("0 - Sair"); 
            System.out.print("Escolha: "); 
            opcao = sc.nextInt(); 
            // Lê um número inteiro digitado e armazena na variável 'opcao'.

            switch (opcao) { 
                // Estrutura de decisão que executa diferentes comandos dependendo do valor digitado.

                case 1: 
                    gp.menu(sc); 
                    // Chama o menu do gerenciador de produtos, passando o Scanner para leitura interna.
                    break; 
                case 2: 
                    gc.menu(sc); 
                    // Chama o menu do gerenciador de clientes.
                    break; 
                case 3:
                    gv.menu(sc); 
                    // Chama o menu do gerenciador de vendas.
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
        // Fecha o Scanner, liberando o recurso usado para leitura de dados.
    }
}
