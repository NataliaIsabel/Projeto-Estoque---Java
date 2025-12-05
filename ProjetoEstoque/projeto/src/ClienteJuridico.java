public class ClienteJuridico implements Cliente {
    private String cnpj;
    private String razaoSocial;
    private int tipo;

    public ClienteJuridico(String cnpj, String razaoSocial, int tipo){
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.tipo = tipo;
    }

    @Override
    public void incluirClientes(){
        
    }

    public void listarClientes(){
        
    }

    public boolean validacao(String cnpj){
        // limpando a String retirando os pontos e virgulas 
        cnpj = cnpj.replace(".", "").replace("/", "").replace("-", "");

        // validação de tamanho ( se for maior que 14, retorna falso)
        if (cnpj.length() != 14) return false;

        // Verifica se todos os dígitos são iguais
        boolean todosIguais = true;
        for (int i = 1; i < 14; i++) {
            if (cnpj.charAt(i) != cnpj.charAt(0)) {
                todosIguais = false;
                break;
            }
        }
        if (todosIguais) return false;

        int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        // Primeiro dígito
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * peso1[i];
        }
        int dig1 = soma % 11;
        dig1 = (dig1 < 2) ? 0 : 11 - dig1;

        // Segundo dígito
        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * peso2[i];
        }
        int dig2 = soma % 11;
        dig2 = (dig2 < 2) ? 0 : 11 - dig2;

        // Comparação entre os digitos 
        // ( primeiro verificador com o primeiro verificador da String 
        // e o segundo verificador com o segundo verificador da String)
            // possui a mesma logica do Cliente Fisico
        return dig1 == (cnpj.charAt(12) - '0') && dig2 == (cnpj.charAt(13) - '0');
    }
}
