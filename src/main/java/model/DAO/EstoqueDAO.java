package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.entidades.ItemEstoque;
import util.Conexao;

public class EstoqueDAO {

    public static void cadastrarItem(ItemEstoque itemEstoque) {
        Connection conn = null;
        String sql = "INSERT INTO ESTOQUE (ID_INGREDIENTE,ID_FORNECEDOR,QUANTIDADE,QUANTIDADE_MINIMA,ID_FILIAL) VALUES (?,?,?,?,?)";

        try {
            conn = Conexao.getConexao(); // Obter a conexão
            conn.setAutoCommit(false); // Desabilitar commit automático
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, itemEstoque.getidIngrediente());
                ps.setInt(2, itemEstoque.getIdFornecedor());
                ps.setInt(3, itemEstoque.getQuantidade());
                ps.setInt(4, itemEstoque.getQuantidadeMinima());
                ps.setInt(5, itemEstoque.getIdFilial());
                ps.executeUpdate();
                System.out.println("Item cadastrado em ESTOQUE com sucesso!");
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
            System.err.println("Erro ao cadastrar item em ESTOQUE: " + e.getMessage());

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

    public static ItemEstoque consultarItem(ItemEstoque item) {
        String sql = "SELECT * FROM ESTOQUE WHERE ID_INGREDIENTE = ? AND ID_FILIAL = ?";

        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, item.getidIngrediente());
            ps.setInt(2, item.getIdFilial());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item.setIdFilial(rs.getInt("ID_FILIAL"));
                    item.setQuantidade(rs.getInt("QUANTIDADE"));
                    item.setQuantidadeMinima(rs.getInt("QUANTIDADE_MINIMA"));
                    item.setIdFornecedor(rs.getInt("ID_FORNECEDOR"));
                    System.out.println("Consulta de item em ESTOQUE realizada com sucesso!");
                } else {
                    System.out.println("Erro ao consultar item em ESTOQUE.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public static void atualizarItem(ItemEstoque item) {
        Connection conn = null;
        String sql = "UPDATE ESTOQUE SET QUANTIDADE = ?, QUANTIDADE_MINIMA = ?, ID_FORNECEDOR = ? WHERE ID_INGREDIENTE = ? AND ID_FILIAL = ?";

        try {
            conn = Conexao.getConexao(); // Obter a conexão
            conn.setAutoCommit(false); // Desabilitar commit automático
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, item.getQuantidade());
                ps.setInt(2, item.getQuantidadeMinima());
                ps.setInt(3, item.getIdFornecedor());
                ps.setInt(4, item.getidIngrediente());
                ps.setInt(5, item.getIdFilial());
                ps.executeUpdate();
                System.out.println("Item atualizado em ESTOQUE com sucesso!");
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
            System.err.println("Erro ao atualizar item em ESTOQUE: " + e.getMessage());

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

    public static void deletarItem(ItemEstoque item) {
        Connection conn = null;
        String sql = "UPDATE ESTOQUE SET QUANTIDADE = 0, QUANTIDADE_MINIMA = 0, ID_FORNECEDOR = 1, ID_INGREDIENTE = 1, ID_FILIAL = 1 WHERE ID_INGREDIENTE = ? AND ID_FILIAL = ?";

        try {
            conn = Conexao.getConexao(); // Obter a conexão
            conn.setAutoCommit(false); // Desabilitar commit automático
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, item.getidIngrediente());
                ps.setInt(2, item.getIdFilial());
                ps.executeUpdate();
                System.out.println("Registros atualizados no estoque para que seja considerado como excluído!");
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
            System.err.println("Erro ao deletar item: " + e.getMessage());

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