package cliente;

public class ClienteJuridico implements Cliente {

    private String razaoSocial;
    private String cnpj;
    private int compras;

    public ClienteJuridico(String razaoSocial, String cnpj) {
        if (!Validacao.validacaoCNPJ(cnpj)) {
            System.out.println("CNPJ inválido!");
        }

        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.compras = 0;
    }

    // métodos gets
    @Override
    public String getIdentificacao() { return cnpj; }

    @Override
    public String getNome() { return razaoSocial; }

    @Override
    public int getTipo() { return 2; }

    @Override
    public int getCompras() { return compras; }

    @Override
    public void adicionarCompra() { compras++; }

    public String toString(){
        return 
        "\n | Jurídico | Razão Social: " + razaoSocial
        + " | CNPJ: " + cnpj
        + " | Qtd Compras: " + compras;
    }


}
