package br.com.restful.dao;

import br.com.restful.factory.Conexao;
import br.com.restful.model.Endereco;
import br.com.restful.model.Evento;
import br.com.restful.model.Instituicao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EventoDAO extends Conexao{

    public EventoDAO() {
        super();
    }
    
    public List<Evento> listarTodos() {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Evento> eventoList = new ArrayList<>();
        String query = "SELECT evento.id, evento.nome, evento.descricao, evento.dataInicio, evento.dataFim " + 
                    "FROM evento " +
                    "INNER JOIN instituicao ON evento.idInstituicao = instituicao.id " +
                    "ORDER BY evento.dataInicio; ";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Evento evento = new Evento();
                Instituicao instituicao = new Instituicao();
                evento.setId(rs.getLong(1));
                evento.setNome(rs.getString(2));
                evento.setDescricao(rs.getString(3));
                evento.setDataInicio(rs.getDate(4));
                evento.setDataFim(rs.getDate(5));

                System.out.println(evento.getNome());
                eventoList.add(evento);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return eventoList;
    }
    
    public List<Instituicao> listarTodasInstituicoes() {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Instituicao> instituicaoList = new ArrayList<Instituicao>();
        String query = "SELECT instituicao.id, instituicao.nome, instituicao.descricao, instituicao.email, instituicao. facebook, " +
                    "instituicao.instagram, instituicao.cnpj, instituicao.website, instituicao.categoria, instituicao.icone, instituicao.fotoDestaque " +
                     "FROM evento " + 
                "INNER JOIN instituicao ON evento.idInstituicao = instituicao.id " +
                "ORDER BY evento.dataInicio ";
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

    public List<Evento> listarTodosComIdEvento(Long id) {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Evento> eventoList = new ArrayList<Evento>();
        String query = "SELECT evento.id, evento.nome, evento.descricao, evento.dataInicio, evento.dataFim, instituicao.nome, instituicao.fotoDestaque, endereco.logradouro, endereco.numero, endereco.cidade, endereco.cep " +
                    "FROM evento " +
                    "INNER JOIN instituicao ON evento.idInstituicao = instituicao.id " +
                    "INNER JOIN endereco ON evento.idEndereco = endereco.id " + 
                    "WHERE evento.id = " + id + " " +
                    "ORDER BY evento.dataInicio;";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Evento evento = new Evento();
                Instituicao instituicao = new Instituicao();
                Endereco endereco = new Endereco();
                evento.setId(rs.getLong(1));
                evento.setNome(rs.getString(2));
                evento.setDescricao(rs.getString(3));
                evento.setDataInicio(rs.getDate(4));
                evento.setDataFim(rs.getDate(5));
                instituicao.setNome(rs.getString(6));
                instituicao.setFotoDestaque(rs.getString(7));
                endereco.setLogradouro(rs.getString(8));
                endereco.setNumero(Integer.parseInt(rs.getString(9)));
                endereco.setCidade(rs.getString(10));
                endereco.setCep(rs.getString(11));
                evento.setEndereco(endereco);
                evento.setInstituicao(instituicao);

                System.out.println(evento.getNome());
                eventoList.add(evento);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return eventoList;
    }
    
    public List<Evento> listarTodosComIdInstituicao(Long id) {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Evento> eventoList = new ArrayList<>();
        String query = "SELECT evento.id, evento.nome, evento.descricao, evento.dataInicio, evento.dataFim " + 
                    "FROM evento " +
                    "INNER JOIN instituicao ON evento.idInstituicao = instituicao.id " + 
                    "WHERE instituicao.id = " + id +
                    " ORDER BY evento.dataInicio; ";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Evento evento = new Evento();
                Instituicao instituicao = new Instituicao();
                evento.setId(rs.getLong(1));
                evento.setNome(rs.getString(2));
                evento.setDescricao(rs.getString(3));
                evento.setDataInicio(rs.getDate(4));
                evento.setDataFim(rs.getDate(5));

                System.out.println(evento.getNome());
                eventoList.add(evento);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return eventoList;
    }
    
    public List<Instituicao> listarInstituicaoPorIdEvento(Long id) {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Instituicao> instituicaoList = new ArrayList<Instituicao>();
        String query = "SELECT instituicao.id, instituicao.nome, instituicao.fotoDestaque "
                + "FROM evento "
                + "INNER JOIN instituicao ON evento.idInstituicao = instituicao.id "
                + "WHERE evento.id = " + id;
                
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Instituicao instituicao = new Instituicao();
                instituicao.setId(rs.getLong(1));
                instituicao.setNome(rs.getString(2));
                instituicao.setFotoDestaque(rs.getString(3));

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
    
    public List<Endereco> listarEnderecoPorIdEvento(Long id) {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Endereco> enderecoList = new ArrayList<Endereco>();
        String query = "SELECT endereco.logradouro, endereco.numero, endereco.cidade, endereco.estado, endereco.cep, endereco.complemento " +
        "FROM evento " +
        "INNER JOIN endereco ON evento.idEndereco = endereco.id " + 
        "WHERE evento.id = " + id + ";";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setLogradouro(rs.getString(1));
                endereco.setNumero(Integer.parseInt(rs.getString(2)));
                endereco.setCidade(rs.getString(3));
                endereco.setEstado(rs.getString(4));
                endereco.setCep(rs.getString(5));
                endereco.setComplemento(rs.getString(6));
                enderecoList.add(endereco);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return enderecoList;
    }
    
   

}
