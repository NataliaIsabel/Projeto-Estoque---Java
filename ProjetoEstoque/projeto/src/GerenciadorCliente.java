import java.util.ArrayList;
import java.util.List;

public class GerenciadorCliente {
    private List<Cliente> clientes = new ArrayList<>();

    public void incluirCliente(Cliente c){
        clientes.add(c);
    }

    public void listarClientesGeral(){
        for (Cliente c : clientes){
            c.listarClientesGeral();
        }
    }
}
