package venda;

// manipulação dos dados 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// importação dos pacotes
import cliente.Cliente;
import produto.Produto;

public class Venda {

    // atributos das vendas
    private int id;
    private LocalDate data;
    private Cliente cliente;
    private Produto produto;
    private int quantidade;

    // construtor
    public Venda(int id, LocalDate dataVenda, Cliente cliente, Produto produto, int quantidade) {
        this.id = id;
        this.data = dataVenda;
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    // método para exibir a lista de vendas
    public String toString(){
        // criação de variavel para guardar formatação da data
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return 
        "\n | ID da venda: " + id
        + "| Data: " + data.format(fmt)
        + "| Nome do cliente: " + cliente.getNome()
        + "| Documento: " + cliente.getIdentificacao() // cpf ou cnpj
        + "\n| ID do produto: " + produto.getId()
        + "| Nome do produto: " + produto.getNome()
        + "| Quant.: " + quantidade + " unidades";
    }
}
