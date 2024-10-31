package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.entidades.Fornecedores;
import util.Conexao;

public class FornecedoresDAO {
    public static void cadastrarFornecedor(Fornecedores fornecedor) {
        String sql = "INSERT INTO FORNECEDORES (CNPJ, NOME, TIPO_MATERIAL, EMAIL, TELEFONE, VALOR_MATERIAL) VALUES (?,?,?,?,?,?)";
        Connection conn = null;

        try {
            conn = Conexao.getConexao(); // Obter a conexão
            conn.setAutoCommit(false); // Desabilitar commit automático
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, fornecedor.getCnpj());
                ps.setString(2, fornecedor.getNome());
                ps.setString(3, fornecedor.getTipoMaterial());
                ps.setString(4, fornecedor.getEmail());
                ps.setString(5, fornecedor.getTelefone());
                ps.setFloat(6, fornecedor.getValorMaterial());
                ps.executeUpdate();
                System.out.println("Fornecedor cadastrado com sucesso!");
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
            System.err.println("Erro ao cadastrar fornecedor: " + e.getMessage());

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

    private static int consultarIdFornecedor(String cnpj) {
        int id = 0;
        String sql = "SELECT ID_FORNECEDOR FROM FORNECEDORES WHERE CNPJ = ?";

        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cnpj);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("ID_FORNECEDOR");
                } else {
                    System.out.println("Erro ao cosutar ID_FORNECEDOR!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public static Fornecedores consultarFornecedor(Fornecedores fornecedor) {
        if (fornecedor.getIdFornecedor() == 0) {
            fornecedor.setIdFornecedor(FornecedoresDAO.consultarIdFornecedor(fornecedor.getCnpj()));
        }
        String sql = "SELECT * FROM FORNECEDORES WHERE ID_FORNECEDOR = ?";

        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, fornecedor.getIdFornecedor());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    fornecedor.setNome(rs.getString("NOME"));
                    fornecedor.setCnpj(rs.getString("CNPJ"));
                    fornecedor.setTipoMaterial(rs.getString("TIPO_MATERIAL"));
                    fornecedor.setEmail(rs.getString("EMAIL"));
                    fornecedor.setTelefone(rs.getString("TELEFONE"));
                    fornecedor.setValorMaterial(rs.getFloat("VALOR_MATERIAL"));
                    System.out.println("Fornecedor consultado com sucesso!");
                } else {
                    System.out.println("Falha ao consultar fornecedor!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fornecedor;
    }

    public static void atualizarFornecedor(Fornecedores fornecedor) {
        if (fornecedor.getIdFornecedor() == 0) {
            fornecedor.setIdFornecedor(FornecedoresDAO.consultarIdFornecedor(fornecedor.getCnpj()));
        }
        String sql = "UPDATE FORNECEDORES SET NOME = ?,TIPO_MATERIAL = ?,EMAIL = ?,TELEFONE = ?,VALOR_MATERIAL = ?, CNPJ = ? WHERE ID_FORNECEDOR = ?";
        Connection conn = null;

        try {
            conn = Conexao.getConexao(); // Obter a conexão
            conn.setAutoCommit(false); // Desabilitar commit automático
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, fornecedor.getNome());
                ps.setString(2, fornecedor.getTipoMaterial());
                ps.setString(3, fornecedor.getEmail());
                ps.setString(4, fornecedor.getTelefone());
                ps.setFloat(5, fornecedor.getValorMaterial());
                ps.setString(6, fornecedor.getCnpj());
                ps.setInt(7, fornecedor.getIdFornecedor());
                ps.executeUpdate();
                System.out.println("Fornecedor atualizado!");

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
            System.err.println("Erro ao atualizar fornecedor: " + e.getMessage());

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

    public static void deletarFornecedor(Fornecedores fornecedor) {
        if (fornecedor.getIdFornecedor() == 0) {
            fornecedor.setIdFornecedor(FornecedoresDAO.consultarIdFornecedor(fornecedor.getCnpj()));
        }
        Connection conn = null;

        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false); // Desabilitar commit automático

            try (PreparedStatement checkEstoque = conn
                    .prepareStatement("SELECT COUNT(*) FROM ESTOQUE WHERE ID_FORNECEDOR = ?")) {
                checkEstoque.setInt(1, fornecedor.getIdFornecedor());
                ResultSet rs = checkEstoque.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    fornecedor.setCnpj(null);
                    fornecedor.setEmail(null);
                    fornecedor.setNome(null);
                    fornecedor.setTelefone(null);
                    fornecedor.setTipoMaterial(null);
                    fornecedor.setValorMaterial(0);
                    FornecedoresDAO.atualizarFornecedor(fornecedor);
                    throw new SQLException(
                            "Não é possível deletar o fornecedor porque ela está referenciado em ESTOQUE, registros serão atualizados como nulos.");
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM FORNECEDORES WHERE ID_FORNECEDOR = ?")) {
                ps.setInt(1, fornecedor.getIdFornecedor());
                ps.executeUpdate();
                System.out.println("Fornecedor deletado com sucesso!");
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
            System.out.println("Erro no método de deletar fornecedor!" + e.getMessage());
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