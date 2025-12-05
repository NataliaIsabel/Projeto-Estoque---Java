import java.util.Scanner;

public class ClienteFisico implements Cliente {
    private String cpf;
    private String nome;

    public ClienteFisico(String cpf, String nome){
        this.cpf = cpf;
        this.nome = nome;
    }

    @Override
    public void incluirClientes(){
        Scanner sc = new Scanner(System.in);

        do {
            System.out.printf("Nome: ");
            nome = sc.nextLine();
        } while (nome.isEmpty() || nome.isBlank());

        System.out.printf("CPF (apenas os números!): ");
        cpf = sc.nextLine();

        do {
            System.out.printf("CPF (apenas os números!): ");
            cpf = sc.nextLine();
        } while (!validacao(cpf));

        
    }

    public void listarClientes(){
        System.out.println("Cliente Físico:");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
    }

    public boolean validacao(String cpf){
        // Limpando a String remove pontos e traços
        cpf = cpf.replace(".", "").replace("-", "");

        // Verifica se tem 11 dígitos
        if (cpf.length() != 11) return false;

        // Verifica se todos os dígitos são iguais (CPF inválido)
        boolean todosIguais = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosIguais = false;
                break;
            }
        }
        if (todosIguais) return false;

        // Calcula primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito > 9) primeiroDigito = 0;

        // Calcula segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito > 9) segundoDigito = 0;

        // Comparação entre os digitos 
        // ( primeiro verificador com o primeiro verificador da String 
        // e o segundo verificador com o segundo verificador da String)
            // obs.: "Cpf.charAt(i) - '0' " <- Está pegando o caracter na posição e convertendo em número
        return (primeiroDigito == (cpf.charAt(9) - '0') && segundoDigito == (cpf.charAt(10) - '0'));
    }

    @Override
    public String getIdentificacao() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIdentificacao'");
    }

    @Override
    public String getNome() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNome'");
    }

    @Override
    public int getTipo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTipo'");
    }

    @Override
    public int getCompras() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCompras'");
    }

}
