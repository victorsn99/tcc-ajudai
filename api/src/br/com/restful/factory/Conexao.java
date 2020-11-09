package br.com.restful.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String databaseName = "ajudaai";
    private static final String url = "jdbc:mysql://localhost:3306/" + databaseName;
    private static final String user = "root";
    private static final String password = "admin";

    public static Connection getConnection() {
        try {
            System.out.println("Conectado");
            Connection conn = null;
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("Classe do Driver não foi encontrada.");
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao se conectar.");
            return null;
        }
    }

    public static void closeConnection(Connection conn) {
        try {
            System.out.println("Desconectado");
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Problema no fechamento da conexão.");
        }
    }

    public static Statement getStatement(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            return stmt;
        } catch (SQLException e) {
            System.out.println("Erro ao obter o Statement.");
            return null;
        }
    }

    public static void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Problema no fechamento do Statement.");
        }
    }

    public static PreparedStatement getPreparedStatement(Connection conn) {
        try {
            PreparedStatement stmt = null;
            return stmt;
        } catch (Exception e) {
            System.out.println("Erro ao obter o PreparedStatement.");
            return null;
        }
    }

    public static void closePreparedStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Problema no fechamento do PreparedStatement.");
        }
    }

    public static void closeResultSet(ResultSet result) {
        try {
            if (result != null) {
                result.close();
            }
        } catch (SQLException e) {
            System.out.println("Problema no fechamento do ResultSet");
        }

    }
}
