package br.com.restful.model;

import java.io.Serializable;
import java.util.List;

public class Instituicao implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String nome;
    
    private String descricao;

    private String cnpj;

    private String categoria;
    
    private String facebook;
    
    private String instagram;

    private String email;
     
    private String website;

    private String icone;

    private String fotoDestaque;
    
    public Instituicao() {
    }

    public Instituicao(Long id, String nome, String descricao, String cnpj, String categoria, String facebook, String instagram, String email, String website, String icone, String destaque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.cnpj = cnpj;
        this.categoria = categoria;
        this.facebook = facebook;
        this.instagram = instagram;
        this.email = email;
        this.website = website;
        this.icone = icone;
        this.fotoDestaque = destaque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getFotoDestaque() {
        return fotoDestaque;
    }

    public void setFotoDestaque(String destaque) {
        this.fotoDestaque = destaque;
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
        if (!(object instanceof Instituicao)) {
            return false;
        }
        Instituicao other = (Instituicao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
