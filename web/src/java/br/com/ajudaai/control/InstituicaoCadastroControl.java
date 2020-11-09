package br.com.ajudaai.control;

import br.com.ajudaai.dao.HibernateUtil;
import br.com.ajudaai.dao.InstituicaoDao;
import br.com.ajudaai.dao.InstituicaoDaoImpl;
import br.com.ajudaai.entidade.Contato;
import br.com.ajudaai.entidade.Evento;
import br.com.ajudaai.entidade.Instituicao;
import br.com.ajudaai.entidade.Perfil;
import br.com.ajudaai.entidade.User;
import br.com.ajudaai.util.Mensagem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;

@ManagedBean(name = "instituicaoCadastroC")
@ViewScoped
//@SessionScoped
public class InstituicaoCadastroControl implements Serializable {

    private InstituicaoDao instituicaoDao;
    private Instituicao instituicao;
    private Session session;
    private User user;
    private Perfil perfil;
    private Contato contato;
    private List<Instituicao> instituicaoMidias;
    private List<Instituicao> instituicaoEventos;

    public InstituicaoCadastroControl() {

    }

    private boolean lerTxt() throws FileNotFoundException, IOException {

        String linha = "";

        BufferedReader buffer = new BufferedReader(new FileReader("D:\\WorkSpace Java\\TCS\\Web Ajudai\\ajudai\\entidades.txt"));
        while (buffer.ready()) {
            linha += buffer.readLine();

        }
        //System.out.println(linha);
        buffer.close();

        boolean contem = linha.contains(instituicao.getNome());

        return contem;

    }

    //cria conta institucional e usuario - SEMPRE PERFIL 1
    public String cadastrarInstituicao() throws IOException {

        session = HibernateUtil.abreConexao();
        boolean contem = lerTxt();
        try {

            instituicaoDao = new InstituicaoDaoImpl();
            instituicao.setUser(user);
            user.setLogin(instituicao.getEmail());
            user.setEnable(true);
            user.setPerfil(new Perfil());
            user.getPerfil().setId(1L);

            if (!contem) {
                user.setEnable(false);
            }

            instituicaoDao.salvarOuAlterar(instituicao, session);

            Mensagem.sucesso("Instituição " + instituicao.getNome());

            instituicao = null;
            user = null;

            System.out.println("Instituição cadastrada com sucesso");

        } catch (RuntimeException e) {

            e.printStackTrace();
            System.out.println("Erro" + e.getMessage());

        } finally {

            session.close();

        }

        String pagina = "/user/dash.faces?faces-redirect=true";
        String msg = "Salvo com sucesso!";

        if (!contem) {
            msg = "Instituição não registrada junto ao Conselho de Assistência Social. Em breve, entraremos em contato!";
            pagina = "register.faces";

        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));

        return pagina;

    }
    
    public Instituicao getInstituicao() {
        if (instituicao == null) {

            instituicao = new Instituicao();

        }
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public User getUser() {

        if (user == null) {

            user = new User();

        }

        // TODO VERIFICAR USUARIO LOGADO
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Perfil getPerfil() {

        if (perfil == null) {

            perfil = new Perfil();
        }

        return perfil;

    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Contato getContato() {

        if (contato == null) {

            contato = new Contato();

        }

        return contato;
    }

    public List<Instituicao> getInstituicaoMidias() {
        if (instituicaoMidias == null) {

            try {
                session = HibernateUtil.abreConexao();
                instituicaoDao = new InstituicaoDaoImpl();
                instituicaoMidias = instituicaoDao.todasCadastradas(session);

            } catch (HibernateException e) {

                System.out.println("Erro" + e.getMessage());

            } finally {
                session.close();
            }
        }
        return instituicaoMidias;
    }

    public List<Instituicao> getInstituicaoEventos() {

        if (instituicaoEventos == null) {

            try {
                session = HibernateUtil.abreConexao();
                instituicaoDao = new InstituicaoDaoImpl();
                instituicaoEventos = instituicaoDao.todasCadastradas(session);
            } catch (HibernateException ex) {

                System.out.println("Erro" + ex.getMessage());

            } finally {
                session.close();
            }

        }
        return instituicaoEventos;
    }
}
