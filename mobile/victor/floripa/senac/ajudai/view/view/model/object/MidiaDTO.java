package victor.floripa.senac.ajudai.view.view.model.object;

import java.io.Serializable;


public class MidiaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String imagem;

    private String descricao;

    private Instituicao instituicao;

    public MidiaDTO() {
    }

    public MidiaDTO(String id, String imagem, String descricao) {
        this.id = id;
        this.imagem = imagem;
        this.descricao = descricao;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
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
        if (!(object instanceof MidiaDTO)) {
            return false;
        }
        MidiaDTO other = (MidiaDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ajudaai.entidade.Midia[ id=" + id + " ]";
    }
    
}
