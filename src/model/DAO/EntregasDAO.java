package src.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.model.entidades.Entregas;
import src.util.Conexao;

public class EntregasDAO {

    public static void cadastrarEntrega(Entregas entrega) {
        String sql = "INSERT INTO ENTREGAS (ENDERECO,DATA_ENTREGA,ID_PEDIDO) VALUES (?,?,?)";
        Connection conn = null;

        try {
            conn = Conexao.getConexao(); // Obter a conexão
            conn.setAutoCommit(false); // Desabilitar commit automático
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entrega.getEndereco());
                ps.setString(2, entrega.getDataEntrega());
                ps.setInt(3, entrega.getIdPedido());
                ps.executeUpdate();
                System.out.println("Entrega cadastrada com sucesso!");
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
            System.err.println("Erro ao cadastrar entrega: " + e.getMessage());

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

    public static Entregas consultarEntrega(Entregas entrega) {
        String sql = "SELECT * FROM ENTREGAS WHERE ID_PEDIDO = ?";

        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, entrega.getIdPedido());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    entrega.setDataEntrega(rs.getString("DATA_ENTREGA"));
                    entrega.setEndereco(rs.getString("ENDERECO"));
                    entrega.setIdEntrega(rs.getInt("ID_ENTREGA"));
                    System.out.println("Consulta de entrega realizada com sucesso!");
                } else {
                    System.out.println("Erro em consulta de entrega!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrega;
    }

    public static Entregas consultarEntrega(int idEntrega) {
        Entregas entrega = new Entregas(null, 0);
        String sql = "SELECT * FROM ENTREGAS WHERE ID_ENTREGA = ?";

        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEntrega);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    entrega.setDataEntrega(rs.getString("DATA_ENTREGA"));
                    entrega.setEndereco(rs.getString("ENDERECO"));
                    entrega.setIdEntrega(idEntrega);
                    entrega.setIdPedido(rs.getInt("ID_PEDIDO"));
                    System.out.println("Consulta de entrega realizada com sucesso!");
                } else {
                    System.out.println("Erro em consulta de entrega!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrega;
    }

    private static Entregas consultarIdEntrega(Entregas entrega) {
        String sql = "SELECT ID_ENTREGA FROM ENTREGAS WHERE ID_PEDIDO = ?";

        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, entrega.getIdPedido());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    entrega.setIdEntrega(rs.getInt("ID_ENTREGA"));
                } else {
                    System.out.println("ID da entrega não encontrado.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrega;
    }

    public static void atualizarEntrega(Entregas entrega) {

        if (entrega.getIdEntrega() == 0) {
            entrega = EntregasDAO.consultarIdEntrega(entrega);
        }

        String sql = "UPDATE ENTREGAS SET DATA_ENTREGA = ?, ENDERECO = ? WHERE ID_ENTREGA = ?";
        Connection conn = null;
        try {
            conn = Conexao.getConexao(); // Obter a conexão
            conn.setAutoCommit(false); // Desabilitar commit automático

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entrega.getDataEntrega());
                ps.setString(2, entrega.getEndereco());
                ps.setInt(3, entrega.getIdEntrega());
                ps.executeUpdate();
                System.out.println("Entrega atualizada com sucesso!");
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
            System.out.println("Erro ao atualizar entrega: " + e.getMessage());
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