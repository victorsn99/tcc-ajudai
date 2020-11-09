package br.com.restful.dao;

import br.com.restful.factory.Conexao;
import br.com.restful.model.Instituicao;
import br.com.restful.model.Midia;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MidiaDAO extends Conexao{

    public MidiaDAO() {
        super();
    }

    public List<Midia> listarTodas(Long id) {
        Connection conn = Conexao.getConnection();
        Statement stmt = Conexao.getStatement(conn);
        ResultSet rs = null;
        List<Midia> midiaList = new ArrayList<Midia>();
        String query = "SELECT midia.id, midia.imagem, midia.descricao " +
                "FROM midia " +
                "INNER JOIN instituicao ON midia.idInstituicao = instituicao.id " +
                "WHERE instituicao.id = " + id + ";";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Midia midia = new Midia();
                midia.setId(rs.getLong(1));
                midia.setImagem(rs.getString(2));
                midia.setDescricao(rs.getString(3));
                midiaList.add(midia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn);
            Conexao.closeStatement(stmt);
            Conexao.closeResultSet(rs);
        }
        return midiaList;
    }

}
