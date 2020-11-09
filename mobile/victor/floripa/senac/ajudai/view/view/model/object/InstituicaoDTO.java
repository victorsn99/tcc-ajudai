package victor.floripa.senac.ajudai.view.view.model.object;

import java.io.Serializable;

public class InstituicaoDTO implements Serializable {

    private String id;

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

    public InstituicaoDTO(String id, String nome, String descricao, String cnpj, String categoria, String facebook, String instagram, String email, String website, String icone, String fotoDestaque) {
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
        this.fotoDestaque = fotoDestaque;
    }

    public InstituicaoDTO() {
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

    public void setFotoDestaque(String fotoDestaque) {
        this.fotoDestaque = fotoDestaque;
    }

    @Override
    public String toString() {
                 return nome + "\n" +
                 descricao + "\n" +
                 categoria + "\n";
    }
}
