import java.util.ArrayList;  // Importa a classe ArrayList, que é usada para armazenar a lista de produtos.
import java.util.List;       // Importa a interface List, que a ArrayList implementa.

public class Produto {
    // Atributos privados da classe Produto
    private String nome;      // Nome do produto
    private int qntInicial;   // Quantidade inicial de estoque do produto
    private double preco;     // Preço do produto
    private int id;           // ID único do produto

    // Lista estática que armazena todos os produtos criados
    private static List<Produto> listaProdutos = new ArrayList<>();
    
    // Contador estático que gera IDs únicos para os produtos, começando de 1
    private static int idCounter = 1;

    // Construtor da classe Produto. É chamado quando criamos um novo produto.
    public Produto(String nome, int qntInicial, double preco) {
        this.nome = nome;             // Atribui o nome do produto
        this.qntInicial = qntInicial; // Atribui a quantidade inicial em estoque
        this.preco = preco;           // Atribui o preço do produto
        this.id = idCounter++;        // Atribui um ID único ao produto e incrementa o contador
    }

    // Métodos Getters para acessar os valores dos atributos
    public String getNome() {
        return nome;  // Retorna o nome do produto
    }

    public int getQntInicial() {
        return qntInicial;  // Retorna a quantidade inicial do produto em estoque
    }

    public double getPreco() {
        return preco;  // Retorna o preço do produto
    }

    public int getId() {
        return id;  // Retorna o ID único do produto
    }

    // Método sobrescrito toString() para retornar uma string com as informações do produto
    @Override
    public String toString() {
        // Retorna uma string formatada com os dados do produto
        // Se a quantidade for zero, adiciona o texto "Sem estoque"
        return "ID: " + id + " | Nome: " + nome + " | Quantidade: " + qntInicial + " | Preço: R$ " + preco +
                (qntInicial == 0 ? " | Sem estoque" : ""); // Verifica se o estoque está zerado
    }

    // Método estático para incluir um novo produto na lista
    public static void incluirProduto(String nome, int qntInicial, double preco) {
        // Verifica se já existe um produto com o mesmo nome na lista
        for (Produto p : listaProdutos) {
            if (p.getNome().equalsIgnoreCase(nome)) {  // Compara sem diferenciar maiúsculas e minúsculas
                System.out.println("Erro: Produto com esse nome já existe.");  // Se encontrar, exibe erro
                return;  // Não adiciona o produto se o nome já existir
            }
        }

        // Cria um novo objeto Produto com os parâmetros fornecidos
        Produto novoProduto = new Produto(nome, qntInicial, preco);
        
        // Adiciona o novo produto à lista de produtos
        listaProdutos.add(novoProduto);

        // Exibe uma mensagem indicando que o produto foi cadastrado com sucesso
        System.out.println("Produto cadastrado com sucesso!");
    }

    // Método estático para excluir um produto da lista, dado o seu ID
    public static void excluirProduto(int id) {
        Produto produto = buscarProdutoPorId(id);  // Tenta encontrar o produto pelo ID

        if (produto != null) {  // Se o produto for encontrado
            listaProdutos.remove(produto);  // Remove o produto da lista
            System.out.println("Produto excluído com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");  // Caso o produto não seja encontrado, exibe erro
        }
    }

    // Método estático para atualizar a quantidade de um produto no estoque
    public static void atualizarEstoque(int id, int quantidade) {
        Produto produto = buscarProdutoPorId(id);  // Tenta encontrar o produto pelo ID
        
        if (produto != null) {  // Se o produto for encontrado
            // Calcula a nova quantidade de estoque
            int novaQuantidade = produto.getQntInicial() + quantidade;  
            
            if (novaQuantidade < 0) {  // Verifica se a nova quantidade seria negativa
                System.out.println("Erro: Estoque não pode ser negativo.");  // Exibe erro se o estoque for negativo
            } else {
                produto.qntInicial = novaQuantidade;  // Atualiza o estoque com a nova quantidade
                System.out.println("Estoque atualizado com sucesso.");
            }
        } else {
            System.out.println("Produto não encontrado.");  // Caso o produto não seja encontrado, exibe erro
        }
    }

    // Método estático para buscar um produto pela sua ID
    public static Produto buscarProdutoPorId(int id) {
        for (Produto produto : listaProdutos) {  // Itera sobre todos os produtos na lista
            if (produto.getId() == id) {  // Se o ID do produto for igual ao fornecido
                return produto;  // Retorna o produto encontrado
            }
        }
        return null;  // Retorna null se nenhum produto com o ID fornecido for encontrado
    }

    // Método estático para buscar um produto pelo nome
    public static Produto buscarProdutoPorNome(String nome) {
        for (Produto produto : listaProdutos) {  // Itera sobre todos os produtos na lista
            if (produto.getNome().toLowerCase().contains(nome.toLowerCase())) {  // Verifica se o nome contém a string fornecida
                return produto;  // Retorna o produto encontrado
            }
        }
        return null;  // Retorna null se nenhum produto com o nome fornecido for encontrado
    }

    // Método estático para listar todos os produtos cadastrados
    public static void listarProdutos() {
        if (listaProdutos.isEmpty()) {  // Se a lista de produtos estiver vazia
            System.out.println("Não há produtos cadastrados.");  // Exibe mensagem de que não há produtos
            return;  // Retorna do método
        }

        // Itera sobre todos os produtos e imprime suas informações
        for (Produto produto : listaProdutos) {
            System.out.println(produto);  // Chama o método toString() para exibir os dados do produto
        }
    }

    // Método estático para calcular o valor total do estoque (soma de quantidade * preço de cada produto)
    public static double calcularValorTotalEstoque() {
        double valorTotal = 0;  // Variável para armazenar o valor total do estoque

        // Itera sobre todos os produtos e soma o valor de cada produto no estoque
        for (Produto produto : listaProdutos) {
            valorTotal += produto.getQntInicial() * produto.getPreco();  // Quantidade * Preço
        }
        
        return valorTotal;  // Retorna o valor total do estoque
    }

    // Método estático para gerar um relatório de estoque baixo, mostrando produtos com quantidade abaixo do limite fornecido
    public static void relatorioEstoqueBaixo(int limite) {
        System.out.println("Produtos com estoque baixo (menor ou igual a " + limite + "):");
        
        // Itera sobre todos os produtos e exibe os produtos com quantidade abaixo do limite
        for (Produto produto : listaProdutos) {
            if (produto.getQntInicial() <= limite) {  // Se a quantidade for menor ou igual ao limite
                System.out.println(produto);  // Exibe o produto
            }
        }
    }
}




