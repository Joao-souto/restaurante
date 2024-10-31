package model.DAO;

import model.entidades.Pratos;
import util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PratosDAO {
        public static void cadastrarPrato(Pratos prato){
        String sql = "INSERT INTO PRATOS (NOME,DESCRICAO,VALOR) VALUES (?,?,?)";
        Connection conn = null;

        try {
            conn = Conexao.getConexao(); // Obter a conexão
            conn.setAutoCommit(false); // Desabilitar commit automático
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, prato.getNome());
                ps.setString(2, prato.getDescricao());
                ps.setFloat(3, prato.getValor());
                ps.executeUpdate();
                System.out.println("Prato cadastrado com sucesso!");

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
            System.err.println("Erro ao cadastrar prato: " + e.getMessage());

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

    public static Pratos consultarPrato(String nome) {
        String sql = "SELECT * FROM PRATOS WHERE NOME = ?";
        Pratos prato = null;

        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    prato = new Pratos(nome, rs.getString("DESCRICAO"), rs.getFloat("VALOR"));
                    prato.setIdPrato(rs.getInt("ID_PRATO"));
                    System.out.println("Prato consultado com sucesso!");
                }else{
                    System.out.println("Erro ao consultar prato por nome!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prato;
    }

    public static Pratos consultarPrato(Pratos prato) {
        if (prato.getIdPrato() == 0) {
            prato = PratosDAO.consultarIdPrato(prato);
        }
        String sql = "SELECT * FROM PRATOS WHERE ID_PRATO = ?";

        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, prato.getIdPrato());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    prato.setDescricao(rs.getString("DESCRICAO"));
                    prato.setNome(rs.getString("NOME"));
                    prato.setValor(rs.getFloat("VALOR"));
                    System.out.println("Consulta de prato realizada com sucesso!");
                }else{
                    System.out.println("Erro na consulta de prato!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prato;
    }

    private static Pratos consultarIdPrato(Pratos prato) {
        String sql = "SELECT ID_PRATO FROM PRATOS WHERE NOME = ?";

        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prato.getNome());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    prato.setIdPrato(rs.getInt("ID_PRATO"));
                }else{
                    System.out.println("Erro na consuta de ID_PRATO!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prato;
    }

    public static void atualizarPrato(Pratos prato) {
        if (prato.getIdPrato() == 0) {
            prato = PratosDAO.consultarIdPrato(prato);
        }
        String sql = "UPDATE PRATOS SET NOME=?,DESCRICAO=?,VALOR=? WHERE ID_PRATO=?";
        Connection conn = null;

        try {
            conn = Conexao.getConexao(); // Obter a conexão
            conn.setAutoCommit(false); // Desabilitar commit automático
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, prato.getNome());
                ps.setString(2, prato.getDescricao());
                ps.setFloat(3, prato.getValor());
                ps.setInt(4, prato.getIdPrato());
                ps.executeUpdate();
                System.out.println("Prato atualizado com sucesso!");
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
            System.err.println("Erro ao atualizar prato: " + e.getMessage());

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

    public static void deletarPrato(Pratos prato){
        if (prato.getIdPrato() == 0) {
            prato = PratosDAO.consultarIdPrato(prato);
        }

        Connection conn = null;

        try {
            conn = Conexao.getConexao(); // Obter a conexão
            conn.setAutoCommit(false); // Desabilitar commit automático

            try (PreparedStatement checkPedidos = conn.prepareStatement("SELECT COUNT(*) FROM PEDIDOS WHERE ID_PRATO = ?")) {
                checkPedidos.setInt(1, prato.getIdPrato());
                ResultSet rs = checkPedidos.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    prato.setDescricao(null);
                    prato.setNome(null);
                    prato.setValor(0);
                    PratosDAO.atualizarPrato(prato);
                    throw new SQLException(
                            "Não é possível deletar o prato porque ela está referenciada em PEDIDOS, registros serão atualizados como nulos.");
                }
            }
    
            try (PreparedStatement deletePrato = conn.prepareStatement("DELETE FROM PRATOS WHERE ID_PRATO = ?")) {
                deletePrato.setInt(1, prato.getIdPrato());
                deletePrato.executeUpdate();
                System.out.println("Prato deletado com sucesso!");
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
            System.err.println("Erro ao deletar prato: " + e.getMessage());

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
