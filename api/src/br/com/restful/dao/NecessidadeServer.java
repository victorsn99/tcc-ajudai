package br.com.restful.dao;

import br.com.restful.factory.Conexao;
import br.com.restful.model.Instituicao;
import br.com.restful.model.Necessidade;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/necessidade")
public class NecessidadeServer {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listAll/{id}")
    public List<Necessidade> listarTodos(@PathParam("id") Long id){
        NecessidadeDAO necessidadeDAO = new NecessidadeDAO();
        List<Necessidade> necessidade = necessidadeDAO.listarTodos(id);
        return necessidade;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listUrgent/{id}")
    public List<Necessidade> listarUrgentes(@PathParam("id") Long id){
        NecessidadeDAO necessidadeDAO = new NecessidadeDAO();
        List<Necessidade> necessidade = necessidadeDAO.listarUrgentes(id);
        return necessidade;
    }

}
