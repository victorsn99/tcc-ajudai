package victor.floripa.senac.ajudai.view.view.control.Tabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.model.object.Comentario;
import victor.floripa.senac.ajudai.view.view.model.object.ComentarioDTO;
import victor.floripa.senac.ajudai.view.view.model.object.Instituicao;
import victor.floripa.senac.ajudai.view.view.util.Constantes;
import victor.floripa.senac.ajudai.view.view.util.RVComentariosAdapter;
import victor.floripa.senac.ajudai.view.view.view.AdicionarComentarioActivity;

public class ComentarioControlTab {

    Activity activity;
    RecyclerView rvComentarios;
    View root;
    ArrayAdapter<Comentario> adapterComentarios;
    List<Comentario> comentarioList;
    List<ComentarioDTO> comentarioDTOList;
    Instituicao instituicao;
    RVComentariosAdapter rvComentariosAdapter;
    LinearLayoutManager llm;
    Comentario comentario;
    Instituicao instituicao2;

    public ComentarioControlTab(View root, Activity activity) {
        this.activity = activity;
        this.root = root;
        inicializar();
    }

    public ComentarioControlTab() {
    }

    public void inicializar(){
        instituicao = (Instituicao) activity.getIntent().getSerializableExtra(Constantes.Params.PRM_INSTITUICAO);
        rvComentarios = root.findViewById(R.id.rvComentarios);
        llm = new LinearLayoutManager(activity);
        rvComentarios.setLayoutManager(llm);
        configCardViewListAll();

    }

    public void configCardViewListAll() {
        rvComentariosAdapter = new RVComentariosAdapter(activity, new ArrayList<Comentario>());
        rvComentarios.setAdapter(rvComentariosAdapter);
        listarComentarios(rvComentariosAdapter);
    }

    private List<Comentario> listarComentarios(final RVComentariosAdapter adapter) {
        AsyncHttpClient client = new AsyncHttpClient();
        comentarioList = new ArrayList<>();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get(Constantes.IPWebService.IP + "comentarios/listAll/" + instituicao.getId(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                Gson gson = new Gson();

                //InstituicaoDTOList instituicaoDTOList = gson.fromJson(s, InstituicaoDTOList.class);

                Type collectionType = new TypeToken<List<ComentarioDTO>>() {
                }.getType();
                comentarioDTOList = gson.fromJson(s, collectionType);

                for (int i = 0; i < comentarioDTOList.size(); i++) {
                    Comentario comentario = new Comentario();
                    comentario.setId(Long.parseLong(comentarioDTOList.get(i).getId()));
                    comentario.setNome(comentarioDTOList.get(i).getNome());
                    comentario.setComentario(comentarioDTOList.get(i).getComentario());
                    comentario.setEstrelas(Integer.parseInt(comentarioDTOList.get(i).getEstrelas()));

                    comentarioList.add(comentario);
                }
                adapter.addAll(comentarioList);

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
        return comentarioList;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        Toast.makeText(activity, "CHEGOU", Toast.LENGTH_LONG).show();
        if (resultCode == activity.RESULT_OK) {
            if (requestCode == Constantes.Params.RQ_COMENTARIO) {
                Comentario comentario = (Comentario) data.getSerializableExtra(Constantes.Params.PRM_COMENTARIO);
                Instituicao instituicao = (Instituicao) data.getSerializableExtra(Constantes.Params.PRM_INSTITUICAO);
                Toast.makeText(activity, "ID: " + comentario.getComentario(), Toast.LENGTH_LONG).show();
                Toast.makeText(activity, "ID: " + instituicao.getId(), Toast.LENGTH_LONG).show();
                cadastrarComentario(comentario, instituicao);
                Toast.makeText(activity, "OK", Toast.LENGTH_SHORT);
            }
        } else if (resultCode == activity.RESULT_CANCELED){
            Toast.makeText(activity, "Cancelado", Toast.LENGTH_SHORT);
        }
    }

    public void telaCadastrarComentario(){
        Intent it = new Intent(activity, AdicionarComentarioActivity.class);
        it.putExtra(Constantes.Params.PRM_INSTITUICAO, instituicao);
        activity.startActivityForResult(it, Constantes.Params.RQ_COMENTARIO);
    }

    public void cadastrarComentario(Comentario comentario, Instituicao instituicao) {

        Gson gson = new Gson();
        RequestParams params = new RequestParams();
        params.put("comentario", gson.toJson(comentario));
        params.put("instituicao", gson.toJson(instituicao));

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.post(Constantes.IPWebService.IP + "comentarios/cadastrar/", params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
                mensagem.setTitle("Mensagem");
                mensagem.setMessage("Comentário cadastrado com sucesso.");
                mensagem.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                    }

                });
                mensagem.show();

            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
                mensagem.setTitle("Mensagem");
                System.out.println(error);
                error.getStackTrace();
                mensagem.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                    }

                });
                mensagem.show();
            }
        });
    }


    public void atualizar() {
        configCardViewListAll();
    }
}
