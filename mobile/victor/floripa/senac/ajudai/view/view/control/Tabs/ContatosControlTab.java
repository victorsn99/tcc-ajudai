package victor.floripa.senac.ajudai.view.view.control.Tabs;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.model.object.Contato;
import victor.floripa.senac.ajudai.view.view.model.object.ContatoDTO;
import victor.floripa.senac.ajudai.view.view.model.object.Instituicao;
import victor.floripa.senac.ajudai.view.view.model.object.InstituicaoDTO;
import victor.floripa.senac.ajudai.view.view.util.Constantes;

public class ContatosControlTab {

    Activity activity;
    View root;
    TextView tvWhatsapp;
    TextView tvFacebook;
    TextView tvInstagram;
    TextView tvWebsite;
    TextView tvEmail;

    Instituicao instituicao;

    List<Contato> contatoList;
    List<ContatoDTO> contatoDTOList;
    ArrayList<Instituicao> instituicaoList;
    ArrayList<InstituicaoDTO> instituicaoDTOList;

    public ContatosControlTab( View root, Activity activity) {
        this.activity = activity;
        this.root = root;
        iniciarComponentes();
    }

    public ContatosControlTab() {
        iniciarComponentes();
    }

    private void iniciarComponentes(){
        instituicao = (Instituicao) activity.getIntent().getSerializableExtra(Constantes.Params.PRM_INSTITUICAO);
        tvWhatsapp = root.findViewById(R.id.whatsapp);
        tvFacebook = root.findViewById(R.id.facebook);
        tvInstagram = root.findViewById(R.id.instagram);
        tvEmail = root.findViewById(R.id.email);
        tvWebsite = root.findViewById(R.id.website);
        resgatarDadosContatos(instituicao.getId());
    }

    private List<Contato> resgatarDadosContatos(Long id) {
        AsyncHttpClient client = new AsyncHttpClient();
        contatoList = new ArrayList<>();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get( Constantes.IPWebService.IP + "contatos/listAll/"+id, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                Gson gson = new Gson();

                Type collectionType = new TypeToken<List<ContatoDTO>>(){}.getType();
                contatoDTOList = gson.fromJson(s, collectionType);

                for (int i = 0; i < contatoDTOList.size(); i++){
                    Contato contato = new Contato();
                    contato.setId(Long.parseLong(contatoDTOList.get(i).getId()));
                    contato.setWhatsapp(contatoDTOList.get(i).getWhatsapp());
                    contato.setTelefone(contatoDTOList.get(i).getTelefone());
                    contatoList.add(contato);
                }
                if (!contatoList.isEmpty()) {
                    tvWhatsapp.setText(contatoList.get(0).getWhatsapp());
                } else {
                    tvWhatsapp.setText("Número não cadastrado");
                }
                listarInstituicao(instituicao.getId());


            }



            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        return contatoList;
    }

    private List<Instituicao> listarInstituicao(Long id) {
        AsyncHttpClient client = new AsyncHttpClient();
        instituicaoList = new ArrayList<>();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get(Constantes.IPWebService.IP + "instituicao/listId/" + id, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                Gson gson = new Gson();

                //InstituicaoDTOList instituicaoDTOList = gson.fromJson(s, InstituicaoDTOList.class);

                Type collectionType = new TypeToken<List<InstituicaoDTO>>() {
                }.getType();
                instituicaoDTOList = gson.fromJson(s, collectionType);

                for (int i = 0; i < instituicaoDTOList.size(); i++) {
                    Instituicao instituicao = new Instituicao();
                    instituicao.setId(Long.parseLong(instituicaoDTOList.get(i).getId()));
                    instituicao.setNome(instituicaoDTOList.get(i).getNome());
                    instituicao.setDescricao(instituicaoDTOList.get(i).getDescricao());
                    instituicao.setCategoria(instituicaoDTOList.get(i).getCategoria());
                    instituicao.setCnpj(instituicaoDTOList.get(i).getCnpj());
                    instituicao.setFacebook(instituicaoDTOList.get(i).getFacebook());
                    instituicao.setInstagram(instituicaoDTOList.get(i).getInstagram());
                    instituicao.setEmail(instituicaoDTOList.get(i).getEmail());
                    instituicao.setWebsite(instituicaoDTOList.get(i).getWebsite());
                    instituicao.setIcone(instituicaoDTOList.get(i).getIcone());
                    instituicao.setDestaque(instituicaoDTOList.get(i).getFotoDestaque());

                    instituicaoList.add(instituicao);


                }

                tvEmail.setText(instituicaoList.get(0).getEmail());
                tvFacebook.setText(instituicaoList.get(0).getFacebook());
                tvInstagram.setText(instituicaoList.get(0).getInstagram());
                tvWebsite.setText(instituicaoList.get(0).getWebsite());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return instituicaoList;
    }
}
