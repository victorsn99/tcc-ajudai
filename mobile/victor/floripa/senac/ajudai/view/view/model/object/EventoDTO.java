package victor.floripa.senac.ajudai.view.view.model.object;

import java.io.Serializable;

public class EventoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String nome;

    private String descricao;

    private String dataInicio;

    private String dataFim;

    private String iconeInstituicao;

    private String destaqueInstituicao;

    private InstituicaoDTO instituicaoDTO;

    private EventoDTO eventoDTO;

    public EventoDTO() {
    }

    public EventoDTO(String id, String nome, String descricao, String dataInicio, String dataFim, String iconeInstituicao, String destaqueInstituicao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.iconeInstituicao = iconeInstituicao;
        this.destaqueInstituicao = destaqueInstituicao;
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

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getIconeInstituicao() {
        return iconeInstituicao;
    }

    public void setIconeInstituicao(String iconeInstituicao) {
        this.iconeInstituicao = iconeInstituicao;
    }

    public String getDestaqueInstituicao() {
        return destaqueInstituicao;
    }

    public void setDestaqueInstituicao(String destaqueInstituicao) {
        this.destaqueInstituicao = destaqueInstituicao;
    }

    public InstituicaoDTO getInstituicaoDTO() {
        return instituicaoDTO;
    }

    public void setInstituicaoDTO(InstituicaoDTO instituicaoDTO) {
        this.instituicaoDTO = instituicaoDTO;
    }

    public EventoDTO getEventoDTO() {
        return eventoDTO;
    }

    public void setEventoDTO(EventoDTO eventoDTO) {
        this.eventoDTO = eventoDTO;
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
        if (!(object instanceof EventoDTO)) {
            return false;
        }
        EventoDTO other = (EventoDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ajudaai.entidade.EventosTab[ id=" + id + " ]";
    }
    
}
