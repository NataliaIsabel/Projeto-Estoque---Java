

package produto;  
// Define que esta classe pertence ao pacote "produto", organizando o projeto em pastas.

// Classe que representa um Produto no sistema.
public class Produto {

    // atributos do produto
    private int id;        
    private String nome;   
    private int qtd;       
    private double preco;  

    // construtor de produtos 
    public Produto(int id, String nome, int qtd, double preco){
        // Atribui aos atributos internos os valores informados no momento da criação do objeto.
        this.id = id;
        this.nome = nome;
        this.qtd = qtd;
        this.preco = preco;
    }

    // métodos get ("manda informação") e set ("altera o dado")
    public int getId(){ return id; }            

    public String getNome() { return nome; }   

    public int getQuantidade() { return qtd; } 

    public double getPreco() { return preco; } 

    public void setQuantidade(int qtd) {        // altera a quantidade em estoque
        this.qtd = qtd;
    }

    // Representação textual do objeto Produto
    public String toString(){
        // Monta uma string contendo todas as informações do produto formatadas
        return 
        "\n | ID: " + id
        + "| Nome: " + nome
        + "| Quant.: " + qtd + " unidades" 
        + "| Preço: R$ " + String.format("%.2f", preco); // formata o preço com 2 casas decimais
    }

}
