package cliente;

public interface Cliente {
    // métodos get para retornar os dados dos clientes
    String getIdentificacao(); //  tipo de cliente: Fisico e Juridico
    String getNome(); // aqui vai retornar o nome ou razao social
    int getTipo(); // 1 para Cliente fisico e 2 para Cliente juridico

    int getCompras();  // variavel que vai guardar qtd de compras
    void adicionarCompra();

}
