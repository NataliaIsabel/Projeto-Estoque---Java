public class Produtos {
    private String nome;
    private int qtdInicial;
    private double preco;
    private int id = 1;

    public Produtos(String nome, int qtdInicial, double preco){
        this.nome = nome;
        this.qtdInicial = qtdInicial;
        this.preco = preco;
        id++;
    }



}
