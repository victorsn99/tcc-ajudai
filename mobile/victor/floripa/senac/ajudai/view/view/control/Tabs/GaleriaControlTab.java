package victor.floripa.senac.ajudai.view.view.control.Tabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.model.object.Instituicao;
import victor.floripa.senac.ajudai.view.view.model.object.Midia;
import victor.floripa.senac.ajudai.view.view.model.object.MidiaDTO;
import victor.floripa.senac.ajudai.view.view.util.Constantes;
import victor.floripa.senac.ajudai.view.view.util.GaleriaImagensAdapter;

public class GaleriaControlTab {

    Activity activity;
    RecyclerView recyclerView;
    Instituicao instituicao;
    List<Midia> midiaList;
    List<MidiaDTO> midiaDTOList;
    GaleriaImagensAdapter adapter;
    LinearLayoutManager llm;
    View root;

    public GaleriaControlTab(View root, Activity activity) {
        this.activity = activity;
        this.root = root;
        iniciarComponentes();
    }

    public GaleriaControlTab() {
    }

    private void iniciarComponentes() {
        instituicao = (Instituicao) activity.getIntent().getSerializableExtra(Constantes.Params.PRM_INSTITUICAO);
        recyclerView = root.findViewById(R.id.rvGaleria);
        llm = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(llm);
        configCardViewListAll();
    }

    public void configCardViewListAll() {
        adapter = new GaleriaImagensAdapter(activity, new ArrayList<Midia>());
        recyclerView.setAdapter(adapter);
        listarImagens(adapter);
    }

    private List<Midia> listarImagens(final GaleriaImagensAdapter adapter) {
        AsyncHttpClient client = new AsyncHttpClient();
        midiaList = new ArrayList<>();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get(Constantes.IPWebService.IP + "midia/listAll/" + instituicao.getId(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                Gson gson = new Gson();

                //InstituicaoDTOList instituicaoDTOList = gson.fromJson(s, InstituicaoDTOList.class);

                Type collectionType = new TypeToken<List<MidiaDTO>>() {
                }.getType();
                midiaDTOList = gson.fromJson(s, collectionType);

                for (int i = 0; i < midiaDTOList.size(); i++) {
                    Midia instituicao = new Midia();
                    instituicao.setId(Long.parseLong(midiaDTOList.get(i).getId()));
                    instituicao.setImagem(midiaDTOList.get(i).getImagem());
                    instituicao.setDescricao(midiaDTOList.get(i).getDescricao());

                    midiaList.add(instituicao);
                }
                adapter.addAll(midiaList);


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
        return midiaList;
    }

    public void atualizarPagina() {
        configCardViewListAll();
    }
}


