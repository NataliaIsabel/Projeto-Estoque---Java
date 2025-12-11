## Documentação do Projeto
Projeto desenvolvido para fins educacionais, na disciplina de Orientação a Objetos.

Responsáveis: Natália Isabel e Giulia Maia.

Professor: Eduardo Leal.

Objetivo: Implementar um sistema de estoque simples utilizando conceitos de orientação a objetos em Java.


## Estrutura do Projeto:
Arvore de diretórios:

```projeto/
│├── src/
││   ├── cliente/
││   │   ├── Cliente.java
││   │   ├── ClienteFisico.java
││   │   ├── ClienteJuridico.java
││   │   ├── Validacao.java
││   │   └── GerenciadorClientes.java
││   ├── produto/
││   │   ├── Produto.java
││   │   └── GerenciadorProdutos.java
││   ├── venda/
││   │   ├── Venda.java
││   │   └── GerenciadorVendas.java
││   └── App.java
│└── README.md
```
## Descrição das classes e pacotes:
- Criação de pacotes (packages) para organizar as classes e interfaces do sistema.

    Pacote `cliente`: Contém interface e classes relacionadas aos clientes do sistema.

    Pacote `produto`: Contém classes relacionadas aos produtos do sistema.

    Pacote `venda`: Contém classes relacionadas às vendas realizadas no sistema.

- Interface:
    * Cliente: Responsável por guardar os metódos que o sistema deve implementar.

- Classes principais:
    * Produto: Representa um produto no estoque, com atributos como nome, preço e quantidade.
    * Cliente Fisico: Representa um cliente físico, com atributos como nome, CPF.
    * Cliente Juridico: Representa um cliente jurídico, com atributos como razão social, CNPJ.
    * Produtos: Responsável por gerenciar a lista de produtos no estoque, incluindo métodos para adicionar, remover e listar produtos.
    * Vendas: Responsável por gerenciar as vendas, incluindo métodos para registrar vendas e listar vendas realizadas.

    * App: Classe principal que contém o método main para executar o sistema.

- Classes auxiliares:
    * Validacao: Contém métodos para validar dados de entrada, como CPF e CNPJ.
    * GerenciadorClientes: Responsável por gerenciar a lista de clientes, incluindo métodos para adicionar, remover e listar clientes.
    * GerenciadorProdutos: Responsável por gerenciar a lista de produtos no estoque.
    * GerenciadorVendas: Responsável por gerenciar as vendas realizadas no sistema.


## Separação do desenvolvimento e produção
As classes foram organizadas para desenvolvimento e produção da seguinte forma:

- `App`: Giulia Maia
- `Cliente Fisico`: Natália Isabel
- `Cliente Juridico`: Natália Isabel
- `Cliente`: Natália Isabel
- `Produtos`: Giulia Maia
- `Vendas`: Giulia Maia e Natália Isabel



