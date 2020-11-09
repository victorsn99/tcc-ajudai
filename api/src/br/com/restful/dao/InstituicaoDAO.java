package br.com.restful.dao;

import br.com.restful.factory.Conexao;
import br.com.restful.model.Instituicao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class InstituicaoDAO extends Conexao{

    public InstituicaoDAO() {
        super();
    }

    public List<Instituicao> listarTodas() {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Instituicao> instituicaoList = new ArrayList<Instituicao>();
        String query = "SELECT instituicao.id, instituicao.nome, instituicao.descricao, instituicao.email, instituicao. facebook, " +
                 "instituicao.instagram, instituicao.cnpj, instituicao.website, instituicao.categoria, instituicao.icone, instituicao.fotoDestaque " +
                "FROM instituicao " + 
                "INNER JOIN user ON instituicao.idUser = user.id " + 
                "WHERE user.enable = true";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Instituicao instituicao = new Instituicao();
                instituicao.setId(rs.getLong(1));
                instituicao.setNome(rs.getString(2));
                instituicao.setDescricao(rs.getString(3));
                instituicao.setEmail(rs.getString(4));
                instituicao.setFacebook(rs.getString(5));
                instituicao.setInstagram(rs.getString(6));
                instituicao.setCnpj(rs.getString(7));
                instituicao.setWebsite(rs.getString(8));
                instituicao.setCategoria(rs.getString(9));
                instituicao.setIcone(rs.getString(10));
                instituicao.setFotoDestaque(rs.getString(11));

                System.out.println(instituicao.getNome());
                System.out.println(instituicao.getFotoDestaque());
                instituicaoList.add(instituicao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return instituicaoList;
    }
    
    public List<Instituicao> listarTodasImagens() {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Instituicao> instituicaoList = new ArrayList<Instituicao>();
        String query = "SELECT instituicao.icone, instituicao.fotoDestaque " +
"                FROM instituicao;";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Instituicao instituicao = new Instituicao();
                instituicao.setIcone(rs.getString(1));
                instituicao.setFotoDestaque(rs.getString(2));
                System.out.println(instituicao.getFotoDestaque());
                instituicaoList.add(instituicao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return instituicaoList;
    }
    
    
    
    public List<Instituicao> listarPorNome(String nome) {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Instituicao> instituicaoList = new ArrayList<Instituicao>();
        String query = "SELECT instituicao.id, instituicao.nome, instituicao.descricao, instituicao.email, instituicao.facebook, " +
                "instituicao.instagram, instituicao.cnpj, instituicao.website, instituicao.categoria, instituicao.icone, instituicao.fotoDestaque   "+
                "FROM instituicao " +
                "WHERE instituicao.nome LIKE '%" + nome + "%';";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Instituicao instituicao = new Instituicao();
                instituicao.setId(rs.getLong(1));
                instituicao.setNome(rs.getString(2));
                instituicao.setDescricao(rs.getString(3));
                instituicao.setEmail(rs.getString(4));
                instituicao.setFacebook(rs.getString(5));
                instituicao.setInstagram(rs.getString(6));
                instituicao.setCnpj(rs.getString(7));
                instituicao.setWebsite(rs.getString(8));
                instituicao.setCategoria(rs.getString(9));
                instituicao.setIcone(rs.getString(10));
                instituicao.setFotoDestaque(rs.getString(11));
                System.out.println(instituicao.getNome());
                instituicaoList.add(instituicao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return instituicaoList;
    }
    
    public List<Instituicao> listarOrdenadoNome() {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Instituicao> instituicaoList = new ArrayList<Instituicao>();
        String query = "SELECT instituicao.id, instituicao.nome, instituicao.descricao, instituicao.email, instituicao.facebook, " +
                "instituicao.instagram, instituicao.cnpj, instituicao.website, instituicao.categoria, instituicao.icone, instituicao.fotoDestaque   "+
                "FROM instituicao " +
                "GROUP BY instituicao.nome";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Instituicao instituicao = new Instituicao();
                instituicao.setId(rs.getLong(1));
                instituicao.setNome(rs.getString(2));
                instituicao.setDescricao(rs.getString(3));
                instituicao.setEmail(rs.getString(4));
                instituicao.setFacebook(rs.getString(5));
                instituicao.setInstagram(rs.getString(6));
                instituicao.setCnpj(rs.getString(7));
                instituicao.setWebsite(rs.getString(8));
                instituicao.setCategoria(rs.getString(9));
                instituicao.setIcone(rs.getString(10));
                instituicao.setFotoDestaque(rs.getString(11));
                System.out.println(instituicao.getNome());
                instituicaoList.add(instituicao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return instituicaoList;
    }
    
    public List<Instituicao> listarOrdenadoCategoria() {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Instituicao> instituicaoList = new ArrayList<Instituicao>();
        String query = "SELECT instituicao.id, instituicao.nome, instituicao.descricao, instituicao.email, instituicao.facebook, " +
                "instituicao.instagram, instituicao.cnpj, instituicao.website, instituicao.categoria, instituicao.icone, instituicao.fotoDestaque   "+
                "FROM instituicao " +
                "ORDER BY instituicao.categoria";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Instituicao instituicao = new Instituicao();
                instituicao.setId(rs.getLong(1));
                instituicao.setNome(rs.getString(2));
                instituicao.setDescricao(rs.getString(3));
                instituicao.setEmail(rs.getString(4));
                instituicao.setFacebook(rs.getString(5));
                instituicao.setInstagram(rs.getString(6));
                instituicao.setCnpj(rs.getString(7));
                instituicao.setWebsite(rs.getString(8));
                instituicao.setCategoria(rs.getString(9));
                instituicao.setIcone(rs.getString(10));
                instituicao.setFotoDestaque(rs.getString(11));
                System.out.println(instituicao.getNome());
                instituicaoList.add(instituicao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return instituicaoList;
    }
    
    public List<Instituicao> listarEspecifica(Long id) {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Instituicao> instituicaoList = new ArrayList<Instituicao>();
        String query = "SELECT instituicao.id, instituicao.nome, instituicao.descricao, instituicao.email, instituicao.facebook, " +
                "instituicao.instagram, instituicao.cnpj, instituicao.website, instituicao.categoria, instituicao.icone, instituicao.fotoDestaque   "+
                "FROM instituicao " +
                "WHERE instituicao.id = " + id + ";";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Instituicao instituicao = new Instituicao();
                instituicao.setId(rs.getLong(1));
                instituicao.setNome(rs.getString(2));
                instituicao.setDescricao(rs.getString(3));
                instituicao.setEmail(rs.getString(4));
                instituicao.setFacebook(rs.getString(5));
                instituicao.setInstagram(rs.getString(6));
                instituicao.setCnpj(rs.getString(7));
                instituicao.setWebsite(rs.getString(8));
                instituicao.setCategoria(rs.getString(9));
                instituicao.setIcone(rs.getString(10));
                instituicao.setFotoDestaque(rs.getString(11));
                instituicaoList.add(instituicao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return instituicaoList;
    }

}
