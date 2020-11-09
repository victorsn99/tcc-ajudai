package br.com.restful.dao;

import br.com.restful.factory.Conexao;
import br.com.restful.model.Instituicao;
import br.com.restful.model.Necessidade;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class NecessidadeDAO extends Conexao{

    public NecessidadeDAO() {
        super();
    }

    public List<Necessidade> listarTodos(Long id) {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Necessidade> necessidadeList = new ArrayList<Necessidade>();
        String query = "SELECT necessidade.id, necessidade.descricao, necessidade.prioridade " + 
                "FROM necessidade " +
                "INNER JOIN instituicao ON necessidade.idInstituicao = instituicao.id " +
                "WHERE instituicao.id =" + id + " " +
                "GROUP BY necessidade.prioridade";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Necessidade necessidade = new Necessidade();
                necessidade.setId(rs.getLong(1));
                necessidade.setDescricao(rs.getString(2));
                necessidade.setPrioridade(rs.getString(3));
                necessidadeList.add(necessidade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return necessidadeList;
    }
    
    public List<Necessidade> listarUrgentes(Long id) {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Necessidade> necessidadeList = new ArrayList<Necessidade>();
        String query = "SELECT necessidade.id, necessidade.descricao, necessidade.prioridade " + 
                "FROM necessidade " +
                "INNER JOIN instituicao ON necessidade.idInstituicao = instituicao.id " +
                "WHERE instituicao.id =" + id + " AND necessidade.prioridade = 'Urgente'";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Necessidade necessidade = new Necessidade();
                necessidade.setId(rs.getLong(1));
                necessidade.setDescricao(rs.getString(2));
                necessidade.setPrioridade(rs.getString(3));
                necessidadeList.add(necessidade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return necessidadeList;
    }
    
    


}
