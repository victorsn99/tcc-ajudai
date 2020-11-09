package victor.floripa.senac.ajudai.view.view.control;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.model.object.Endereco;
import victor.floripa.senac.ajudai.view.view.model.object.EnderecoDTO;
import victor.floripa.senac.ajudai.view.view.model.object.Evento;
import victor.floripa.senac.ajudai.view.view.model.object.EventoDTO;
import victor.floripa.senac.ajudai.view.view.model.object.Instituicao;
import victor.floripa.senac.ajudai.view.view.model.object.InstituicaoDTO;
import victor.floripa.senac.ajudai.view.view.util.Constantes;
import victor.floripa.senac.ajudai.view.view.view.InstituicaoActivity;

public class EventoControl {

    Activity activity;
    TextView tvEndereco;
    TextView tvNomeEvento;
    TextView tvNomeInstituicao;
    TextView tvDataInicio;
    TextView tvDataFim;
    TextView tvDescricao;
    Evento evento;
    ImageView destaque;

    Drawable dDestaque;

    List<Evento> eventoList;
    List<EventoDTO> eventoDTOList;

    List<Endereco> enderecoList;
    List<EnderecoDTO> enderecoDTOList;

    List<Instituicao> instituicaoList;
    List<InstituicaoDTO> instituicaoDTOList;

    public EventoControl(Activity activity) {
        this.activity = activity;
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        evento = (Evento) activity.getIntent().getSerializableExtra(Constantes.Params.PRM_EVENTO);
        destaque = activity.findViewById(R.id.foto_instituicao_evento);
        tvEndereco = activity.findViewById(R.id.endereco_evento);
        tvNomeEvento = activity.findViewById(R.id.nome_evento);
        tvNomeInstituicao = activity.findViewById(R.id.nome_instituicao_evento);
        tvDataInicio = activity.findViewById(R.id.hora_inicio_evento);
        tvDataFim = activity.findViewById(R.id.hora_termino_evento);
        tvDescricao = activity.findViewById(R.id.descricao_evento);
        listarTodosEventos();
        listarTodasInstituicoes();
        listarEnderecos();
        Toast.makeText(activity, " " + evento.getId(), Toast.LENGTH_LONG).show();

    }

    public void perfilInstituicao() {
        Intent it = new Intent(activity, InstituicaoActivity.class);
        activity.startActivity(it);
    }

    private List<Evento> listarTodosEventos() {
        eventoList = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get(Constantes.IPWebService.IP + "evento/listAllByIdEvent/" + evento.getId(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                Gson gson = new Gson();

                //InstituicaoDTOList instituicaoDTOList = gson.fromJson(s, InstituicaoDTOList.class);

                Type collectionType = new TypeToken<List<EventoDTO>>() {
                }.getType();
                eventoDTOList = gson.fromJson(s, collectionType);

                for (int i = 0; i < eventoDTOList.size(); i++) {
                    Evento evento = new Evento();
                    evento.setId(Long.parseLong(eventoDTOList.get(i).getId()));
                    evento.setDescricao(eventoDTOList.get(i).getDescricao());
                    evento.setNome(eventoDTOList.get(i).getNome());
                    evento.setDataInicio(Date.valueOf(eventoDTOList.get(i).getDataInicio()));
                    evento.setDataFim(Date.valueOf(eventoDTOList.get(i).getDataFim()));
                    eventoList.add(evento);
                }
                SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String dataInicio = dtf.format(eventoList.get(0).getDataInicio());
                String dataFim = dtf.format(eventoList.get(0).getDataFim());

                tvNomeEvento.setText(eventoList.get(0).getNome());
                tvDataInicio.setText("Início: " + dataInicio);
                tvDataFim.setText("Fim: " + dataFim);
                tvDescricao.setText(eventoList.get(0).getDescricao());


            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return eventoList;
    }

    private List<Instituicao> listarTodasInstituicoes() {
        instituicaoList = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get(Constantes.IPWebService.IP + "evento/listAllInstByIdEvent/" + evento.getId(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                Gson gson = new Gson();

                Type collectionType = new TypeToken<List<InstituicaoDTO>>() {
                }.getType();
                instituicaoDTOList = gson.fromJson(s, collectionType);

                for (int i = 0; i < instituicaoDTOList.size(); i++) {
                    Instituicao instituicao = new Instituicao();
                    instituicao.setId(Long.parseLong(instituicaoDTOList.get(i).getId()));
                    instituicao.setNome(instituicaoDTOList.get(i).getNome());
                    carregarDestaque(instituicaoDTOList.get(i).getFotoDestaque());
                    destaque.setImageDrawable(dDestaque);
                    instituicaoList.add(instituicao);
                }

                tvNomeInstituicao.setText(instituicaoList.get(0).getNome());


            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return instituicaoList;
    }

    public List<Endereco> listarEnderecos (){
        AsyncHttpClient client = new AsyncHttpClient();
        enderecoList = new ArrayList<>();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get( Constantes.IPWebService.IP + "evento/listAllAdressesByIdEvent/"+evento.getId(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                Gson gson = new Gson();

                Type collectionType = new TypeToken<List<EnderecoDTO>>(){}.getType();
                enderecoDTOList = gson.fromJson(s, collectionType);

                for (int i = 0; i < enderecoDTOList.size(); i++){
                    Endereco endereco = new Endereco();
                    endereco.setLogradouro(enderecoDTOList.get(i).getLogradouro());
                    endereco.setNumero(Integer.parseInt(enderecoDTOList.get(i).getNumero()));
                    endereco.setCep(enderecoDTOList.get(i).getCep());
                    endereco.setCidade(enderecoDTOList.get(i).getCidade());
                    endereco.setComplemento(enderecoDTOList.get(i).getComplemento());
                    enderecoList.add(endereco);
                }
                tvEndereco.setText("Endereço: " + enderecoList.get(0).getLogradouro() + ", "
                        + enderecoList.get(0).getNumero() + ", " + enderecoList.get(0).getCep() + ", "
                        + enderecoList.get(0).getCidade() + " - " + enderecoList.get(0).getComplemento());


            }



            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        return enderecoList;
    }

    public void carregarDestaque(final String urlDestaque) {
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
    }


    public void irAteInstituicao() {
        Intent it = new Intent(activity, InstituicaoActivity.class);
        it.putExtra(Constantes.Params.PRM_INSTITUICAO, instituicaoList.get(0));
        Toast.makeText(activity, " " + instituicaoList.get(0).getId(), Toast.LENGTH_LONG).show();
        activity.startActivity(it);
    }
}
