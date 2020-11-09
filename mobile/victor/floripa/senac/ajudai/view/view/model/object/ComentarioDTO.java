package victor.floripa.senac.ajudai.view.view.model.object;

import java.io.Serializable;

public class ComentarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String nome;

    private String comentario;

    private String estrelas;

    public ComentarioDTO() {
    }

    public ComentarioDTO(String id, String nome, String comentario, String estrelas) {
        this.id = id;
        this.nome = nome;
        this.comentario = comentario;
        this.estrelas = estrelas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(String estrelas) {
        this.estrelas = estrelas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComentarioDTO)) {
            return false;
        }
        ComentarioDTO other = (ComentarioDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ajudaai.entidade.ComentarioControlTab[ id=" + id + " ]";
    }
    
}
