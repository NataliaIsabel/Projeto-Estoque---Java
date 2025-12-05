public class ClienteJuridico implements Clientes {
    private double cnpj;
    private String razaoSocial;

    public ClienteJuridico(String nome, String razaoSocial){
        this.cnpj = nome;
        this.razaoSocial = razaoSocial;;
    }


    @Override
    //incluir clientes
    public void incluirCliente(){
        
    }

    //listar clientes
    public void listarCliente() {

    }


    //validação
    public void validacao() {

    }
}


