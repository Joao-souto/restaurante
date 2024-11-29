package src.controller;

import src.model.DAO.ClientesDAO;
import src.model.entidades.Clientes;
import java.util.regex.Pattern;

public class ClientesController {

    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("\\d{2}-\\d{9}");

    public static Clientes validarCliente(Clientes cliente) {
        
        // Validação dos campos obrigatórios
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            cliente.setValid(false);
            cliente.setMensagem("Erro: CPF é obrigatório.");
            return cliente;
        }

        if (!CPF_PATTERN.matcher(cliente.getCpf()).matches()) {
            cliente.setValid(false);
            cliente.setMensagem("Erro: CPF inválido. Formato esperado: xxx.xxx.xxx-xx");
            return cliente;
        }

        if (ClientesDAO.cpfExiste(cliente.getCpf())) {
            cliente.setValid(false);
            cliente.setMensagem("Erro: CPF já cadastrado.");
            return cliente;
        }

        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            cliente.setValid(false);
            cliente.setMensagem("Erro: Email é obrigatório.");
            return cliente;
        }

        if (!EMAIL_PATTERN.matcher(cliente.getEmail()).matches()) {
            cliente.setValid(false);
            cliente.setMensagem("Erro: Email inválido.");
            return cliente;
        }

        if (ClientesDAO.emailExiste(cliente.getEmail())) {
            cliente.setValid(false);
            cliente.setMensagem("Erro: Email já cadastrado.");
            return cliente;
        }

        if (cliente.getIdade() <= 0) {
            cliente.setValid(false);
            cliente.setMensagem("Erro: Idade deve ser um número positivo.");
            return cliente;
        }

        // Se passou em todas as validações
        cliente.setValid(true);
        cliente.setMensagem("Validação bem-sucedida!");
        ClientesDAO.cadastrarCliente(cliente);
        return cliente;
    }
}
