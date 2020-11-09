package br.com.restful.dao;

import br.com.restful.factory.Conexao;
import br.com.restful.model.Contato;
import br.com.restful.model.Instituicao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ContatosDAO extends Conexao{

    public ContatosDAO() {
        super();
    }
    
    public List<Contato> listarTodos(Long id) {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Contato> contatosList = new ArrayList<Contato>();
        String query = "SELECT contato.id, contato.telefone, contato.whatsapp " +  
                    "FROM contato " +
                    "INNER JOIN instituicao ON contato.idInstituicao = instituicao.id " +
                    "WHERE instituicao.id =" + id + ";";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Contato contato = new Contato();
                Instituicao instituicao = new Instituicao();
                contato.setId(rs.getLong(1));
                contato.setTelefone(rs.getString(2));
                contato.setWhatsapp(rs.getString(3));
                contatosList.add(contato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return contatosList;
    }

}
