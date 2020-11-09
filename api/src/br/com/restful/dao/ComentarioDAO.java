package br.com.restful.dao;

import br.com.restful.factory.Conexao;
import br.com.restful.model.Comentario;
import br.com.restful.model.Instituicao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO extends Conexao {

    public ComentarioDAO() {
        super();
    }

    public List<Comentario> listarTodos(Long id) {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Comentario> comentarioList = new ArrayList<Comentario>();
        String query = "SELECT comentario.id, comentario.nome, comentario.comentario, comentario.estrelas "
                + "FROM comentario "
                + "INNER JOIN instituicao ON comentario.idInstituicao = instituicao.id "
                + "WHERE instituicao.id = " + id + ";";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Comentario comentario = new Comentario();
                comentario.setId(rs.getLong(1));
                comentario.setNome(rs.getString(2));
                comentario.setComentario(rs.getString(3));
                comentario.setEstrelas(rs.getInt(4));

                System.out.println(comentario.getNome());
                comentarioList.add(comentario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return comentarioList;
    }

    public List<Comentario> listarPorNome(Long id, String nome) {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Comentario> comentarioList = new ArrayList<Comentario>();
        String query = "SELECT comentario.id, comentario.nome, comentario.comentario "
                + "FROM comentario "
                + "INNER JOIN instituicao ON comentario.idInstituicao = instituicao.id "
                + "WHERE instituicao.id = " + id + " AND comentario.nome = '" + nome + "';";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Comentario comentario = new Comentario();
                comentario.setId(rs.getLong(1));
                comentario.setNome(rs.getString(2));
                comentario.setComentario(rs.getString(3));

                System.out.println(comentario.getNome());
                comentarioList.add(comentario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return comentarioList;
    }

    public int cadastrar(Comentario comentario, Instituicao instituicao) {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        int r = 0;
            String query = "INSERT INTO comentario(nome, comentario, estrelas, idInstituicao) "
                    + "VALUES('" + comentario.getNome() + "', '"+ comentario.getComentario() +"', '" + comentario.getEstrelas() + "', '" + 
                            instituicao.getId() + "');";
            try {
                r = stmt.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println("Erro");
            } finally {
                Conexao.closeConnection(conn);
                Conexao.closeStatement(stmt);
            }
        return r;
    }
//        Connection conn = Conexao.getConnection();
//        Statement stmt = Conexao.getStatement(conn);
//        int r = 0;
//        String query = "INSERT INTO comentario(nome, comentario, estrelas) VALUES '" + comentario.getNome() + "', '" + comentario.getComentario() +
//                "', '" + comentario.getEstrelas() + "';";
//        try {
//           stmt.executeUpdate(query);
//           
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Conexao.closeConnection(conn);
//            Conexao.closeStatement(stmt);
//        }
//        return r;
//    };

}
