package produto;

public class Produto {
    // atributos do produto
    private int id;
    private String nome;
    private int qtd;
    private double preco;

    // construtor de produtos 
    public Produto(int id, String nome, int qtd, double preco){
        // esse ID recebe os dados que serão passados no parametro do construtor
        this.id = id;
        this.nome = nome;
        this.qtd = qtd;
        this.preco = preco;
    }


    // métodos get ("manda informação") e set("declarar o dado")
    public int getId(){ return id; }

    public String getNome() { return nome; }

    public int getQuantidade() { return qtd; }

    public double getPreco() { return preco; }

    public void setQuantidade(int qtd) { this.qtd = qtd; }


    
    public String toString(){
        return 
        "\n | ID: " + id
        + "| Nome: " + nome
        + "| Quant.: " + qtd + " unidades" 
        + "| Preço: R$ " + String.format("%.2f", preco);
    }

    

}
