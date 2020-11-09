package br.com.restful.dao;

import br.com.restful.factory.Conexao;
import br.com.restful.model.Instituicao;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/instituicao")
public class InstituicaoServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listAll")
    public List<Instituicao> listarTodas(){
        InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
        return instituicaoDAO.listarTodas();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listAllPhotos")
    public List<Instituicao> listarTodasImagens(){
        InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
        return instituicaoDAO.listarTodasImagens();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listNome/{nome}")
    public List<Instituicao> listarPorNome(@PathParam("nome") String nome){
        InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
        List<Instituicao> instituicao = instituicaoDAO.listarPorNome(nome);
        return instituicao;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/order/nome")
    public List<Instituicao> listarOrdenadoNome(){
        InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
        return instituicaoDAO.listarOrdenadoNome();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/order/categoria")
    public List<Instituicao> listarOrdenadoCategoria(){
        InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
        return instituicaoDAO.listarOrdenadoCategoria();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listId/{id}")
    public List<Instituicao> listarEspecifica(@PathParam("id") Long id){
        InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
        List<Instituicao> instituicao = instituicaoDAO.listarEspecifica(id);
        return instituicao;
    }

}
