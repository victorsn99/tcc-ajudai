/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.restful.dao;

import br.com.restful.model.Comentario;
import br.com.restful.model.Contato;
import br.com.restful.model.Instituicao;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author victo
 */
@Path("/comentarios")
public class ComentarioServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listAll/{id}")
    public List<Comentario> listarTodas(@PathParam("id") Long id) {
        ComentarioDAO comentarioDAO = new ComentarioDAO();
        List<Comentario> comentario = comentarioDAO.listarTodos(id);
        return comentario;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrar")
    public int cadastrar(@FormParam("comentario") String comentario, @FormParam("instituicao") String instituicao) {
        Gson gson = new Gson();
        Comentario comentario1 = gson.fromJson(comentario, Comentario.class);
        Instituicao instituicao2 = gson.fromJson(instituicao, Instituicao.class);
        ComentarioDAO comentarioDAO = new ComentarioDAO();;
        return comentarioDAO.cadastrar(comentario1, instituicao2);
    }

}
