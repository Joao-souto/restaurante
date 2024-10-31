package app;

import model.DAO.ClientesDAO;
import model.entidades.Clientes;

public class App {
    public static void main(String[] args) {
        Clientes clienteTeste = new Clientes(null, null, null, null, 0, null, null, null);
        clienteTeste.setIdCliente(5);
        clienteTeste = ClientesDAO.consultarCliente(clienteTeste);
        clienteTeste.visualizarCliente();
    }
}
