package cliente;

public class ClienteFisico implements Cliente {

    private String nome;
    private String cpf;
    private int compras;

    public ClienteFisico(String nome, String cpf) {
        // Validação do CPF (Se não for válido, exibe mensagem de erro)
        if (!Validacao.validacaoCPF(cpf)) {
            System.out.println("CPF inválido!");
            return;
        }

        this.nome = nome;
        this.cpf = cpf;
        this.compras = 0;
    }

    // métodos gets
    @Override
    public String getIdentificacao() {
        return cpf;
    }

    public String getNome() { return nome; }

    public int getTipo() { return 1; }

    public int getCompras() { return compras; }

    public void adicionarCompra() { compras++; }

    public String toString(){
        return 
        "\n | Físico | Nome: " + nome
        + " | CPF: " + cpf 
        + " | Qtd Compras: " + compras;
    }

  
}
