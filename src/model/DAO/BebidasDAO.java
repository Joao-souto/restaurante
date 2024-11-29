package src.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.model.entidades.Bebidas;
import src.util.Conexao;

public class BebidasDAO {

    public static void cadastrarBebida(Bebidas bebida) {
        String sql = "INSERT INTO BEBIDAS (NOME,DESCRICAO,VALOR) VALUES (?,?,?)";
        Connection conn = null;
        try {
            conn = Conexao.getConexao(); // Obter a conexão
            conn.setAutoCommit(false); // Desabilitar commit automático
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, bebida.getNome());
                ps.setString(2, bebida.getDescricao());
                ps.setFloat(3, bebida.getValor());
                ps.executeUpdate(); // Melhor usar executeUpdate para operações de atualização
                System.out.println("Bebida cadastrada com sucesso!");
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
            System.err.println("Erro ao cadastrar a bebida: " + e.getMessage());

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

    private static Bebidas consultarIdBebida(Bebidas bebida) {
        String sql = "SELECT ID_BEBIDA FROM BEBIDAS WHERE NOME = ?";

        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bebida.getNome());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    bebida.setIdBebida(rs.getInt("ID_BEBIDA"));
                } else {
                    System.out.println("ID da bebida não encontrado.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bebida;
    }

    public static Bebidas consultarBebida(Bebidas bebida) {
        if (bebida.getIdBebida() == 0) {
            bebida = BebidasDAO.consultarIdBebida(bebida);
        }
        String sql = "SELECT * FROM BEBIDAS WHERE ID_BEBIDA = ?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bebida.getIdBebida());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    bebida.setNome(rs.getString("NOME"));
                    bebida.setDescricao(rs.getString("DESCRICAO"));
                    bebida.setValor(rs.getFloat("VALOR"));
                    System.out.println("Bebida encontrada!");
                } else {
                    System.out.println("Bebida não encontrada.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bebida;
    }

    public static Bebidas consultarBebida(String nome) {
        String sql = "SELECT * FROM BEBIDAS WHERE NOME = ?";
        Bebidas bebida = new Bebidas(nome, sql, 0);
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    bebida.setNome(rs.getString("NOME"));
                    bebida.setDescricao(rs.getString("DESCRICAO"));
                    bebida.setValor(rs.getFloat("VALOR"));
                    bebida.setIdBebida(rs.getInt("ID_BEBIDA"));
                    System.out.println("Bebida encontrada!");
                } else {
                    System.out.println("Bebida não encontrada.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bebida;
    }

    public static void atualizarBebida(Bebidas bebida) {
        if (bebida.getIdBebida() == 0) {
            bebida = BebidasDAO.consultarIdBebida(bebida);
        }
        String sql = "UPDATE BEBIDAS SET NOME=?, DESCRICAO=?, VALOR=? WHERE ID_BEBIDA=?";
        Connection conn = null;
        try {
            conn = Conexao.getConexao(); // Obter a conexão
            conn.setAutoCommit(false); // Desabilitar commit automático

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, bebida.getNome());
                ps.setString(2, bebida.getDescricao());
                ps.setFloat(3, bebida.getValor());
                ps.setInt(4, bebida.getIdBebida());
                ps.executeUpdate();
                System.out.println("Bebida atualizada!");
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
            System.out.println("Erro ao atualizar bebida: " + e.getMessage());
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

    // MÉTODOS PARA O DELETE
    private static void atualizarBebidaNula(Bebidas bebida, Connection conn) {

        String sql = "UPDATE BEBIDAS SET NOME=?, DESCRICAO=?, VALOR=? WHERE ID_BEBIDA=?";
        try {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, bebida.getNome());
                ps.setString(2, bebida.getDescricao());
                ps.setFloat(3, bebida.getValor());
                ps.setInt(4, bebida.getIdBebida());
                ps.executeUpdate();
                System.out.println("Bebida atualizada como nula!");
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
            System.err.println("Erro ao atualizar bebida como nula: " + e.getMessage());
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

    private static void setarNulo(Bebidas bebida, Connection conn) {
        bebida.setDescricao(null);
        bebida.setNome(null);
        bebida.setValor(0);
        BebidasDAO.atualizarBebidaNula(bebida, conn);
    }

    public static void deletarBebida(Bebidas bebida) {
        if (bebida.getIdBebida() == 0) {
            bebida = BebidasDAO.consultarIdBebida(bebida);
        }

        Connection conn = null; // Inicializar a conexão fora do try
        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false); // Desabilitar commit automático

            // Verificar se existem registros na tabela de pedidos que referenciam a bebida
            try (PreparedStatement checkPedidos = conn
                    .prepareStatement("SELECT COUNT(*) FROM PEDIDOS WHERE ID_BEBIDA = ?")) {
                checkPedidos.setInt(1, bebida.getIdBebida());
                try (ResultSet rs = checkPedidos.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        BebidasDAO.setarNulo(bebida, conn);
                        throw new SQLException(
                                "Não é possível deletar a bebida porque ela está referenciada em PEDIDOS, registros serão atualizados como nulos.");
                    }
                }
            }

            // Deletar a bebida da tabela BEBIDAS se não houver registros relacionados
            try (PreparedStatement deleteBebida = conn.prepareStatement("DELETE FROM BEBIDAS WHERE ID_BEBIDA = ?")) {
                deleteBebida.setInt(1, bebida.getIdBebida());
                deleteBebida.executeUpdate();
                System.out.println("Bebida deletada com sucesso!");
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
            System.out.println("Erro ao deletar bebida: " + e.getMessage());
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