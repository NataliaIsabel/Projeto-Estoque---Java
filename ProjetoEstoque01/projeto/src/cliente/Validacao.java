package cliente;


public class Validacao {

        public static boolean validacaoCPF(String cpf){
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



        public static boolean validacaoCNPJ(String cnpj){
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
