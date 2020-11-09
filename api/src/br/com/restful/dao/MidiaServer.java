package br.com.restful.dao;

import br.com.restful.factory.Conexao;
import br.com.restful.model.Instituicao;
import br.com.restful.model.Midia;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/midia")
public class MidiaServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listAll/{id}")
    public List<Midia> listarTodas(@PathParam("id") Long id){
        MidiaDAO midiaDAO = new MidiaDAO();
        List<Midia> midia = midiaDAO.listarTodas(id);
        return midia;
    }

}
