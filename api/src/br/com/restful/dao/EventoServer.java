package br.com.restful.dao;

import br.com.restful.factory.Conexao;
import br.com.restful.model.Endereco;
import br.com.restful.model.Evento;
import br.com.restful.model.Instituicao;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/evento")
public class EventoServer {

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listAll")
    public List<Evento> listarTodas(){
        EventoDAO eventoDAO = new EventoDAO();
        List<Evento> evento = eventoDAO.listarTodos();
        return evento;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listAllInst")
    public List<Instituicao> listarTodasInstituicoes(){
        EventoDAO eventoDAO = new EventoDAO();
        List<Instituicao> evento = eventoDAO.listarTodasInstituicoes();
        return evento;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listAllByIdEvent/{id}")
    public List<Evento> listarTodosEventos(@PathParam("id") Long id){
        EventoDAO eventoDAO = new EventoDAO();
        List<Evento> evento = eventoDAO.listarTodosComIdEvento(id);
        return evento;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listAllByIdInst/{id}")
    public List<Evento> listarTodosInstituicao(@PathParam("id") Long id){
        EventoDAO eventoDAO = new EventoDAO();
        List<Evento> evento = eventoDAO.listarTodosComIdInstituicao(id);
        return evento;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listAllInstByIdEvent/{id}")
    public List<Instituicao> listarPorIdEvento(@PathParam("id") Long id){
        EventoDAO eventoDAO = new EventoDAO();
        List<Instituicao> evento = eventoDAO.listarInstituicaoPorIdEvento(id);
        return evento;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listAllAdressesByIdEvent/{id}")
    public List<Endereco> listarPorIdEventoEndereco(@PathParam("id") Long id){
        EventoDAO eventoDAO = new EventoDAO();
        List<Endereco> evento = eventoDAO.listarEnderecoPorIdEvento(id);
        return evento;
    }

}
