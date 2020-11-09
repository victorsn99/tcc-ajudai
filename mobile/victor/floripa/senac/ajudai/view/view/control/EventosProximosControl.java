package victor.floripa.senac.ajudai.view.view.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.model.object.Evento;
import victor.floripa.senac.ajudai.view.view.model.object.EventoDTO;
import victor.floripa.senac.ajudai.view.view.model.object.Instituicao;
import victor.floripa.senac.ajudai.view.view.model.object.InstituicaoDTO;
import victor.floripa.senac.ajudai.view.view.util.Constantes;
import victor.floripa.senac.ajudai.view.view.util.RVEventosAdapter;
import victor.floripa.senac.ajudai.view.view.view.EventoActivity;

public class EventosProximosControl {

    Activity activity;
    List<Evento> lista;
    RecyclerView recyclerView;
    List<Instituicao> listaI;
    List<InstituicaoDTO> instituicaoDTOList = new ArrayList<>();
    List<EventoDTO> eventoDTOList = new ArrayList<>();
    private RVEventosAdapter rvEventosAdapter;
    private LinearLayoutManager llm;

    public EventosProximosControl(Activity activity){
        this.activity = activity;
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        recyclerView = activity.findViewById(R.id.rvEventosProximos);
        llm = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(llm);
        listarTodasInstituicoes();
    }

    private RVEventosAdapter.EventoOnClickListener onClickEvento() {
        return new RVEventosAdapter.EventoOnClickListener() {
            @Override
            public void OnClickEvento(View view, int position) {
                Evento e = lista.get(position);
                Toast.makeText(activity, e.getId() + " " + e.getNome(), Toast.LENGTH_LONG);
            }
        };
    }

    private void configurarCardView() {
        rvEventosAdapter = new RVEventosAdapter(activity, new ArrayList<Evento>(), onClickEvento());
        recyclerView.setAdapter(rvEventosAdapter);
        listarTodas(rvEventosAdapter);
    }

    private List<Evento> listarTodas(final RVEventosAdapter adapter) {
        lista = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get(Constantes.IPWebService.IP + "evento/listAll", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                Gson gson = new Gson();

                //InstituicaoDTOList instituicaoDTOList = gson.fromJson(s, InstituicaoDTOList.class);

                Type collectionType = new TypeToken<List<EventoDTO>>(){}.getType();
                eventoDTOList = gson.fromJson(s, collectionType);

                for (int i =0; i < eventoDTOList.size(); i++) {
                    Evento evento = new Evento();
                    evento.setId(Long.parseLong(eventoDTOList.get(i).getId()));
                    evento.setDescricao(eventoDTOList.get(i).getDescricao());
                    evento.setNome(eventoDTOList.get(i).getNome());
                    evento.setDataInicio(Date.valueOf(eventoDTOList.get(i).getDataInicio()));
                    evento.setDataFim(Date.valueOf(eventoDTOList.get(i).getDataFim()));
                    Instituicao inst = new Instituicao();
                    inst.setIcone(listaI.get(i).getIcone());
                    evento.setInstituicao(inst);
                    lista.add(evento);
                }
                adapter.addAll(lista);

            }



            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return lista;
    }

    private List<Instituicao> listarTodasInstituicoes() {
        AsyncHttpClient client = new AsyncHttpClient();
        listaI = new ArrayList<>();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get(Constantes.IPWebService.IP + "evento/listAllInst", new AsyncHttpResponseHandler() {
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
                    listaI.add(instituicao);
                }
                configurarCardView();



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
        return listaI;
    }





    public void telaEvento(View view){
        Evento item = null;
        for (int i=0; i < rvEventosAdapter.getItemCount(); i++){
            Evento evento = rvEventosAdapter.getItem(i);
            String nome = ((TextView) view.findViewById(R.id.tvNome_EventosProximos)).getText().toString();

            if(nome.equals(evento.getNome())){
                item = evento;
                break;
            }

        }

        //Toast.makeText(activity, "" + item.getNome(), Toast.LENGTH_LONG).show();

        if(item!=null){
            Intent it = new Intent(activity, EventoActivity.class);
            it.putExtra(Constantes.Params.PRM_EVENTO, item);
            activity.startActivity(it);
        }

    }


}
