package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.entidades.Ingredientes;
import util.Conexao;

public class IngredientesDAO {

    public static void cadastrarIngrediente(Ingredientes ingrediente) {
        String sql = "INSERT INTO INGREDIENTES (NOME, UNIDADE_MEDIDA) VALUES (?,?)";
        Connection conn = null;

        try {
            conn = Conexao.getConexao(); // Obter a conexão
            conn.setAutoCommit(false); // Desabilitar commit automático
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, ingrediente.getNome());
                ps.setString(2, ingrediente.getUnidadeDeMedida());
                ps.executeUpdate();
                System.out.println("Ingrediente cadastrado com sucesso!");

            }
            conn.commit(); // Realizar o commit das operações
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Desfazer as operações em caso de erro
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            System.err.println("Erro ao cadastrar ingrediente: " + e.getMessage());

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Restaurar o auto commit
                    conn.close(); // Fechar a conexão manualmente
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    private static int consultarIdIngrediente(String nome) {
        int id = 0;
        String sql = "SELECT ID_INGREDIENTE FROM INGREDIENTES WHERE NOME = ?";

        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("ID_INGREDIENTE");
                }else{
                    System.out.println("ID_INGREDIENTE não encontrado!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public static Ingredientes consultarIngrediente(Ingredientes ingrediente) {
        if (ingrediente.getIdIngrediente() == 0) {
            ingrediente.setIdIngrediente(IngredientesDAO.consultarIdIngrediente(ingrediente.getNome()));
        }
        String sql = "SELECT * FROM INGREDIENTES WHERE ID_INGREDIENTE = ?";

        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ingrediente.getIdIngrediente());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ingrediente.setNome(rs.getString("NOME"));
                    ingrediente.setUnidadeDeMedida(rs.getString("UNIDADE_MEDIDA"));
                    System.out.println("Consulta de ingrediente realizada com sucesso!");
                }else{
                    System.out.println("Ingrediente não encontrado!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingrediente;
    }

    public static void atualizarIngrediente(Ingredientes ingrediente) {
        if (ingrediente.getIdIngrediente() == 0) {
            ingrediente.setIdIngrediente(IngredientesDAO.consultarIdIngrediente(ingrediente.getNome()));
        }
        String sql = "UPDATE INGREDIENTES SET NOME = ?, UNIDADE_MEDIDA = ? WHERE ID_INGREDIENTE = ?";
        Connection conn = null;

        try {
            conn = Conexao.getConexao(); // Obter a conexão
            conn.setAutoCommit(false); // Desabilitar commit automático
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, ingrediente.getNome());
                ps.setString(2, ingrediente.getUnidadeDeMedida());
                ps.setInt(3, ingrediente.getIdIngrediente());
                ps.executeUpdate();
                System.out.println("Ingrediente atualizado!");

            }
            conn.commit(); // Realizar o commit das operações
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Desfazer as operações em caso de erro
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            System.err.println("Erro ao atualizar ingrediente: " + e.getMessage());

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Restaurar o auto commit
                    conn.close(); // Fechar a conexão manualmente
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    public static void deletarIngrediente(Ingredientes ingrediente) {
        if (ingrediente.getIdIngrediente() == 0) {
            ingrediente.setIdIngrediente(IngredientesDAO.consultarIdIngrediente(ingrediente.getNome()));
        }

        Connection conn = null; // Inicializar a conexão fora do try
        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false); // Desabilitar commit automático

        // VERIFICA SE HÁ REGISTROS RELACIONADOS NA TABELA DE ESTOQUE
        try (PreparedStatement checkEstoque = conn.prepareStatement("SELECT COUNT(*) FROM ESTOQUE WHERE ID_INGREDIENTE = ?")) {
            checkEstoque.setInt(1, ingrediente.getIdIngrediente());
            ResultSet rs = checkEstoque.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                ingrediente.setNome(null);
                ingrediente.setUnidadeDeMedida(null);
                IngredientesDAO.atualizarIngrediente(ingrediente);
                throw new SQLException(
                "Não é possível deletar o ingrediente porque ele está referenciado em ESTOQUE, registros serão atualizados como nulos.");
            }
        }
        try (PreparedStatement deleteIngrediente = conn.prepareStatement("DELETE FROM INGREDIENTES WHERE ID_INGREDIENTE = ?")) {
            deleteIngrediente.setInt(1, ingrediente.getIdIngrediente());
            deleteIngrediente.executeUpdate();
            System.out.println("Ingrediente deletado com sucesso!");
        }

            conn.commit(); // Realizar o commit das operações
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Desfazer as operações em caso de erro
                    System.out.println("Rollback realizado devido a erro: " + e.getMessage());
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Restaurar o auto commit
                    conn.close(); // Fechar a conexão manualmente
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }
}