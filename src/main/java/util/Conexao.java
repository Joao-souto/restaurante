package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String url = "jdbc:mysql://localhost:3306/RESTAURANTE";
    private static final String usuario = "root";
    private static final String senha = "";

    private static Connection conn;

    // Método estático que retorna a conexão com o banco de dados
    public static synchronized Connection getConexao() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, usuario, senha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
