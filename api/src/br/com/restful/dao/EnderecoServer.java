package br.com.restful.dao;

import br.com.restful.factory.Conexao;
import br.com.restful.model.Endereco;
import br.com.restful.model.Instituicao;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/endereco")
public class EnderecoServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listAll/{id}")
    public List<Endereco> listarTodas(@PathParam("id") Long id){
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        List<Endereco> endereco = enderecoDAO.listarTodos(id);
        return endereco;
    }

}
