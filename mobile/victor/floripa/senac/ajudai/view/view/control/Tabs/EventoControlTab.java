package victor.floripa.senac.ajudai.view.view.control.Tabs;

import android.app.Activity;
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
import victor.floripa.senac.ajudai.view.view.util.RVEventosInstituicaoAdapter;
import victor.floripa.senac.ajudai.view.view.view.EventoActivity;

public class EventoControlTab {

    Activity activity;
    View root;
    List<Evento> lista;
    Instituicao instituicao;
    RecyclerView recyclerView;
    List<InstituicaoDTO> instituicaoDTOList = new ArrayList<>();
    List<EventoDTO> eventoDTOList = new ArrayList<>();
    RVEventosInstituicaoAdapter adapter;
    LinearLayoutManager llm;

    public EventoControlTab(Activity activity, View root) {
        this.activity = activity;
        this.root = root;
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        instituicao = (Instituicao) activity.getIntent().getSerializableExtra(Constantes.Params.PRM_INSTITUICAO);
        Toast.makeText(activity, "" +instituicao.getId(), Toast.LENGTH_LONG).show();
        recyclerView = root.findViewById(R.id.rvEventosInstituicao);
        llm = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(llm);
        configurarCardView();
    }

    private RVEventosInstituicaoAdapter.EventoOnClickListener onClickEvento() {
        return new RVEventosInstituicaoAdapter.EventoOnClickListener() {
            @Override
            public void OnClickEvento(View view, int position) {
                Evento e = lista.get(position);
                Toast.makeText(activity, e.getId() + " " + e.getNome(), Toast.LENGTH_LONG);
            }
        };
    }

    private void configurarCardView() {
        adapter = new RVEventosInstituicaoAdapter(activity, new ArrayList<Evento>(), onClickEvento());
        recyclerView.setAdapter(adapter);
        listarTodas(adapter);
    }

    private List<Evento> listarTodas(final RVEventosInstituicaoAdapter adapter) {
        lista = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get(Constantes.IPWebService.IP + "evento/listAllByIdInst/" + instituicao.getId(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                Gson gson = new Gson();

                Type collectionType = new TypeToken<List<EventoDTO>>(){}.getType();
                eventoDTOList = gson.fromJson(s, collectionType);

                for (int i =0; i < eventoDTOList.size(); i++) {
                    Evento evento = new Evento();
                    evento.setId(Long.parseLong(eventoDTOList.get(i).getId()));
                    evento.setDescricao(eventoDTOList.get(i).getDescricao());
                    evento.setNome(eventoDTOList.get(i).getNome());
                    evento.setDataInicio(Date.valueOf(eventoDTOList.get(i).getDataInicio()));
                    evento.setDataFim(Date.valueOf(eventoDTOList.get(i).getDataFim()));
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



    public void telaEvento(View view){
        Evento item = null;
        for (int i=0; i < adapter.getItemCount(); i++){
            Evento evento = adapter.getItem(i);
            String nome = ((TextView) view.findViewById(R.id.tvNome_EventoInstituicao)).getText().toString();

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

    public void atualizarPagina() {
        configurarCardView();
        Toast.makeText(activity, "Atualizando...", Toast.LENGTH_LONG);
    }
}


