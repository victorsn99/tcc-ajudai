package br.com.restful.dao;

import br.com.restful.factory.Conexao;
import br.com.restful.model.Contato;
import br.com.restful.model.Instituicao;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/contatos")
public class ContatosServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listAll/{id}")
    public List<Contato> listarTodas(@PathParam("id") Long id){
        ContatosDAO contatoDAO = new ContatosDAO();
        List<Contato> contato = contatoDAO.listarTodos(id);
        return contato;
    }

}
