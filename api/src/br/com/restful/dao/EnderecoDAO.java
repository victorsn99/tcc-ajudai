package br.com.restful.dao;

import br.com.restful.factory.Conexao;
import br.com.restful.model.Endereco;
import br.com.restful.model.Instituicao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EnderecoDAO extends Conexao{

    public EnderecoDAO() {
        super();
    }

    public List<Endereco> listarTodos(Long id) {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Endereco> enderecoList = new ArrayList<Endereco>();
        String query = "SELECT endereco.id, endereco.logradouro, endereco.numero, endereco.cidade, endereco.estado, endereco.cep, endereco.complemento " +
        "FROM endereco " +
        "INNER JOIN instituicao ON endereco.idInstituicao = instituicao.id " + 
        "WHERE instituicao.id = " + id + ";";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getLong(1));
                endereco.setLogradouro(rs.getString(2));
                endereco.setNumero(Integer.parseInt(rs.getString(3)));
                endereco.setCidade(rs.getString(4));
                endereco.setEstado(rs.getString(5));
                endereco.setCep(rs.getString(6));
                endereco.setComplemento(rs.getString(7));
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
