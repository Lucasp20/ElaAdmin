package br.com.ela_admin.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FabricaConexao implements Serializable {

    public static Connection abrirConexao() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.
                getConnection("jdbc:mysql://localhost:3306/quarta_fase"
               + "?useTimezone=true&serverTimezone=America/Sao_Paulo&zeroDateTimeBehavior=convertToNull",
                        "root", "1107");
    }

    public static void fecharConexao(Connection conn, Statement stmt) {
        fechar(conn, stmt, null);
    }

    public static void fecharConexao(Connection conn, Statement stmt, ResultSet rs) {
        fechar(conn, stmt, rs);
    }

    private static void fechar(Connection conn, Statement stmt, ResultSet rs) {
        try {
            conn.close();
            stmt.close();
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexao " + ex.getMessage());
        }
    }
}
