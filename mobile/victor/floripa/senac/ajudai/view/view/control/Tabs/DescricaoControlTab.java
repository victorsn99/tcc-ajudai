package victor.floripa.senac.ajudai.view.view.control.Tabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.model.object.Endereco;
import victor.floripa.senac.ajudai.view.view.model.object.EnderecoDTO;
import victor.floripa.senac.ajudai.view.view.model.object.Instituicao;
import victor.floripa.senac.ajudai.view.view.model.object.InstituicaoDTO;
import victor.floripa.senac.ajudai.view.view.model.object.Necessidade;
import victor.floripa.senac.ajudai.view.view.model.object.NecessidadeDTO;
import victor.floripa.senac.ajudai.view.view.util.Constantes;

public class DescricaoControlTab {

    private View root;
    Activity activity;
    TextView tvDescricao;
    TextView tvTituloActivity;
    TextView tvTitulo;
    ListView lvEndereco;
    ListView lvNecessidades;
    ImageView imgDestaque;

    Drawable dDestaque;
    List<Necessidade> necessidadeList = new ArrayList<>();
    List<NecessidadeDTO> necessidadeDTOList = new ArrayList<>();

    List<Instituicao> instituicaoList = new ArrayList<>();
    List<InstituicaoDTO> instituicaoDTOList = new ArrayList<>();

    List<Endereco> enderecoList = new ArrayList<>();
    List<EnderecoDTO> enderecoDTOList = new ArrayList<>();
    ArrayAdapter<Necessidade> adapterNecessidades;
    ArrayAdapter<Endereco> adapterEnderecos;
    Necessidade necessidade;
    Instituicao instituicao;
    public DescricaoControlTab(View root, Activity activity) {
        this.root = root;
        this.activity = activity;
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        instituicao = (Instituicao) activity.getIntent().getSerializableExtra(Constantes.Params.PRM_INSTITUICAO);
        tvTituloActivity = activity.findViewById(R.id.nomeInstituicaoTelaInstituicao);
        tvDescricao = root.findViewById(R.id.descricaoTelaInstituicao);
        tvTitulo = root.findViewById(R.id.tituloTelaInstituicao);
        lvEndereco = root.findViewById(R.id.lvEnderecos);
        lvNecessidades = root.findViewById(R.id.lvNecessidadesTelaInstituicao);
        imgDestaque = root.findViewById(R.id.imagemDestaqueTelaInstituicao);
        listarInstituicao(instituicao.getId());
        adapterNecessidades = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, resgatarDadosNecessidades());
        lvNecessidades.setAdapter(adapterNecessidades);
        adapterEnderecos = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, resgatarDadosEnderecos());
        lvEndereco.setAdapter(adapterEnderecos);
    }

    private void carregarDestaque(final String urlDestaque) {
        final Target targetDestaque = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d("tag", "on bitmap loaded");
                dDestaque = new BitmapDrawable(activity.getResources(), bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.d("tag", "on bitmap failed");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.d("tag", "on prepare load");
            }
        };
        Picasso.with(activity).load(urlDestaque).placeholder(dDestaque).into(targetDestaque);
        imgDestaque.setImageDrawable(dDestaque);
    }


    private List<Instituicao> listarInstituicao(Long id) {
        AsyncHttpClient client = new AsyncHttpClient();
        instituicaoList = new ArrayList<>();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get(Constantes.IPWebService.IP + "instituicao/listId/" + id, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                Toast.makeText(activity, "Iniciando", Toast.LENGTH_SHORT).show();
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

                    System.out.println("NOME: "+instituicaoDTOList.get(i).getNome());
                    System.out.println("CAT: "+instituicaoDTOList.get(i).getCategoria());
                    System.out.println("WS: "+instituicaoDTOList.get(i).getWebsite());
                    System.out.println("FB: "+instituicaoDTOList.get(i).getFacebook());
                    System.out.println("IC: "+instituicaoDTOList.get(i).getIcone());
                    System.out.println("EMAIL: "+instituicaoDTOList.get(i).getEmail());
                    System.out.println("DSC: "+instituicaoDTOList.get(i).getDescricao());
                    System.out.println("DESTAQUE: "+instituicaoDTOList.get(i).getFotoDestaque());

                    instituicaoList.add(instituicao);

                    carregarDestaque(instituicao.getDestaque());


                }
                tvTituloActivity.setText(instituicaoList.get(0).getNome());
                tvDescricao.setText(instituicaoList.get(0).getDescricao());
                tvTitulo.setText(instituicaoList.get(0).getNome());

            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
                mensagem.setTitle("Mensagem");
                mensagem.setPositiveButton("Erro ao se conectar com o SGDB" + error, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                    }

                });
                mensagem.show();
            }
        });
        return instituicaoList;
    }

    private List<Necessidade> resgatarDadosNecessidades() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get(Constantes.IPWebService.IP + "necessidade/listAll/"+instituicao.getId(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                Gson gson = new Gson();

                Type collectionType = new TypeToken<List<NecessidadeDTO>>(){}.getType();
                necessidadeDTOList = gson.fromJson(s, collectionType);

                for (int i = 0; i < necessidadeDTOList.size(); i++){
                    Necessidade necessidade = new Necessidade();
                    necessidade.setId(Long.parseLong(necessidadeDTOList.get(i).getId()));
                    necessidade.setDescricao(necessidadeDTOList.get(i).getDescricao());
                    necessidade.setPrioridade(necessidadeDTOList.get(i).getPrioridade());
                    adapterNecessidades.add(necessidade);
                }






            }

            @Override
            public void onPostProcessResponse(ResponseHandlerInterface instance, HttpResponse response) {
                super.onPostProcessResponse(instance, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return necessidadeList;
    }

    private List<Endereco> resgatarDadosEnderecos() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get( Constantes.IPWebService.IP + "endereco/listAll/"+instituicao.getId(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                Gson gson = new Gson();

                Type collectionType = new TypeToken<List<EnderecoDTO>>(){}.getType();
                enderecoDTOList = gson.fromJson(s, collectionType);

                for (int i = 0; i < enderecoDTOList.size(); i++){
                    Endereco endereco = new Endereco();
                    endereco.setId(Long.parseLong(enderecoDTOList.get(i).getId()));
                    endereco.setLogradouro(enderecoDTOList.get(i).getLogradouro());
                    endereco.setNumero(Integer.parseInt(enderecoDTOList.get(i).getNumero()));
                    endereco.setCep(enderecoDTOList.get(i).getCep());
                    endereco.setCidade(enderecoDTOList.get(i).getCidade());
                    endereco.setEstado(enderecoDTOList.get(i).getEstado());
                    endereco.setComplemento(enderecoDTOList.get(i).getComplemento());
                    enderecoList.add(endereco);
                    adapterEnderecos.add(endereco);
                }


            }



            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        return enderecoList;
    }



    private void cliqueCurto(){
        lvNecessidades.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                necessidade = adapterNecessidades.getItem(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Detalhes");
                alerta.setMessage("Necessidade: " + necessidade.getDescricao() + "\nPrioridade: " + necessidade.getPrioridade());
                alerta.setNeutralButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alerta.show();
            }
        });
    }
}
