package victor.floripa.senac.ajudai.view.view.model.object;

import java.io.Serializable;

public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String telefone;
    
    private String whatsapp;

    private Instituicao instituicao;
    
    public Contato() {
    }

    public Contato(Long id, String telefone, String whatsapp) {
        this.id = id;
        this.telefone = telefone;
        this.whatsapp = whatsapp;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
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
        if (!(object instanceof Contato)) {
            return false;
        }
        Contato other = (Contato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ajudaai.entidade.Contato[ id=" + id + " ]";
    }
    
}
