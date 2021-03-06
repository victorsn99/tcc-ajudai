package br.com.restful.model;

import java.io.Serializable;


public class Necessidade implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String descricao;
    
    private String prioridade;
    
    private boolean status;

    private Instituicao instituicao;

    public Necessidade() {
    }

    public Necessidade(String descricao, String prioridade, boolean status) {
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = status;
    
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
        if (!(object instanceof Necessidade)) {
            return false;
        }
        Necessidade other = (Necessidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ajudaai.entidade.Necessidade[ id=" + id + " ]";
    }
    
}
