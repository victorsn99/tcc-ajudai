package victor.floripa.senac.ajudai.view.view.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
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
import victor.floripa.senac.ajudai.view.view.model.object.InstituicaoDTO;
import victor.floripa.senac.ajudai.view.view.util.Constantes;
import victor.floripa.senac.ajudai.view.view.util.OnClickInterface;
import victor.floripa.senac.ajudai.view.view.util.RVInstituicoesAdapter;
import victor.floripa.senac.ajudai.view.view.view.ConfiguracoesActivity;
import victor.floripa.senac.ajudai.view.view.view.EventosProximosActivity;
import victor.floripa.senac.ajudai.view.view.view.InstituicaoActivity;
import victor.floripa.senac.ajudai.view.view.view.InstituicoesProximasActivity;
import victor.floripa.senac.ajudai.view.view.view.SuporteActivity;

public class MainControl {
    public static Activity activity;
    CardView cardView;
    RecyclerView recyclerView;

    List<Instituicao> lista = new ArrayList<>();

    ArrayAdapter<InstituicaoDTO> adapterInstituicoesDTO;
    TextView tvNome, tvDescricao;
    ImageView icone, destaque;
    List<InstituicaoDTO> instituicaoDTOList;
    LinearLayoutManager llm;
    RVInstituicoesAdapter rvInstituicoesAdapter;

    OnClickInterface onClickInterface;

    public MainControl(Activity activity) {
        this.activity = activity;
        iniciarComponentes();
    }

    public MainControl() {
    }

    public void iniciarComponentes() {
        cardView = activity.findViewById(R.id.cvInstituicoes);
        recyclerView = activity.findViewById(R.id.rvInstituicoes);
        tvNome = activity.findViewById(R.id.nomeInstituicao);
        tvDescricao = activity.findViewById(R.id.descricaoInstituicao);
        icone = activity.findViewById(R.id.icone);
        destaque = activity.findViewById(R.id.fotoDestaque);
        llm = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(llm);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Clicou", Toast.LENGTH_LONG);
            }
        });
        configCardViewListAll();
    }

    private RVInstituicoesAdapter.InstituicoesOnClickListener onClickInstituicao() {
        return new RVInstituicoesAdapter.InstituicoesOnClickListener() {

            @Override
            public void OnClickInstituicao(View view, int position) {
                Instituicao i = lista.get(position);
                Toast.makeText(activity, i.getId() + " " + i.getNome(), Toast.LENGTH_LONG);

            }
        };
    }

    private void configCardViewListAll() {
        rvInstituicoesAdapter = new RVInstituicoesAdapter(activity, new ArrayList<Instituicao>(), onClickInstituicao());
        recyclerView.setAdapter(rvInstituicoesAdapter);
        listarTodasInstituicoes(rvInstituicoesAdapter);
    }

    private void configCardViewOrdenado(String input) {
        rvInstituicoesAdapter = new RVInstituicoesAdapter(activity, new ArrayList<Instituicao>(), onClickInstituicao());
        recyclerView.setAdapter(rvInstituicoesAdapter);
        ordenar(input, rvInstituicoesAdapter);
    }

    private void configCardViewPesquisa(String input) {
        if (!input.isEmpty()) {
            rvInstituicoesAdapter = new RVInstituicoesAdapter(activity, new ArrayList<Instituicao>(), onClickInstituicao());
            recyclerView.setAdapter(rvInstituicoesAdapter);
            pesquisar(input, rvInstituicoesAdapter);
        } else {
            final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
            mensagem.setTitle("Mensagem");
            mensagem.setMessage("Digite o nome da instituição.");
            mensagem.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    atualizarPagina();
                }

            });
            mensagem.show();
        }
    }

    private List<Instituicao> listarTodasInstituicoes(final RVInstituicoesAdapter adapter) {
        AsyncHttpClient client = new AsyncHttpClient();
        lista = new ArrayList<>();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get(Constantes.IPWebService.IP + "instituicao/listAll", new AsyncHttpResponseHandler() {
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

                    lista.add(instituicao);
                }

                adapter.addAll(lista);


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
        return lista;
    }

    public void telaMapa() {
        Intent it = new Intent(activity, InstituicoesProximasActivity.class);
        activity.startActivity(it);
    }

    public void telaConfiguracoesApp() {
        Intent it = new Intent(activity, ConfiguracoesActivity.class);
        activity.startActivity(it);
    }

    public void telaInstituicao(View view) {
        Instituicao item = null;
        for (int i=0; i<rvInstituicoesAdapter.getItemCount(); i++){
            Instituicao inst = rvInstituicoesAdapter.getItem(i);
            String nome = ((TextView) view.findViewById(R.id.nomeInstituicao)).getText().toString();

            if(nome.equals(inst.getNome())){
                item = inst;
                break;
            }

        }



        if(item!=null){
            Intent it = new Intent(activity, InstituicaoActivity.class);
            it.putExtra(Constantes.Params.PRM_INSTITUICAO, item);
            activity.startActivity(it);
        }

    }

    public void telaEventosProximos() {
        Intent it = new Intent(activity, EventosProximosActivity.class);
        activity.startActivity(it);
    }

    public void telaSuporte() {
        Intent it = new Intent(activity, SuporteActivity.class);
        activity.startActivityForResult(it, 1);
    }

    public void pesquisarInstituicao() {

        final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
        mensagem.setTitle("Pesquisar instituição");
        // DECLARACAO DO EDITTEXT
        final EditText input = new EditText(activity);
        input.setHint("Nome da instituição");
        mensagem.setView(input);
        mensagem.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(activity, input.getText().toString().trim(),
                        Toast.LENGTH_SHORT).show();
                InputMethodManager imm;
                imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
                configCardViewPesquisa(input.getText().toString().trim());
            }

        });

        mensagem.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

            }

        });

        mensagem.show();
        // FORÇA O TECLADO APARECER AO ABRIR O ALERT
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void filtroInstituicoes() {

        final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
        mensagem.setTitle("Filtrar instituições por...");

        final Spinner input = new Spinner(activity);
        ArrayAdapter<String> adapterInput;
        List<String> categorias = new ArrayList<>();
        categorias.add("Nome");
        categorias.add("Categoria");

        adapterInput = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item,
                categorias);
        adapterInput.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input.setAdapter(adapterInput);

        mensagem.setView(input);
        mensagem.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(activity, input.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT).show();
                configCardViewOrdenado(input.getSelectedItem().toString());
            }

        });

        mensagem.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

            }

        });
        mensagem.show();

    }

    private void ordenar(String input, final RVInstituicoesAdapter adapter) {
        if (input.equals("Nome")) {
            lista = new ArrayList<>();
            AsyncHttpClient client = new AsyncHttpClient();
            client.addHeader("user-agent", "Mozilla Chrome");
            client.get(Constantes.IPWebService.IP + "instituicao/order/nome", new AsyncHttpResponseHandler() {
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


                        lista.add(instituicao);
                    }

                    adapter.addAll(lista);


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
        } else if (input.equals("Categoria")) {
            lista = new ArrayList<>();
            AsyncHttpClient client = new AsyncHttpClient();
            client.addHeader("user-agent", "Mozilla Chrome");
            client.get(Constantes.IPWebService.IP + "instituicao/order/categoria", new AsyncHttpResponseHandler() {
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


                        lista.add(instituicao);
                    }

                    adapter.addAll(lista);


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


        }


    }

    private void pesquisar(final String inputText, final RVInstituicoesAdapter adapter) {
        lista = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get(Constantes.IPWebService.IP + "instituicao/listNome/" + inputText, new AsyncHttpResponseHandler() {
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


                    lista.add(instituicao);
                }

                adapter.addAll(lista);
                if (lista.size() == 0) {
                    final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
                    mensagem.setTitle("Mensagem");
                    mensagem.setMessage("Não foi encontrado nenhuma instituição com o nome '" + inputText + "'.");
                    mensagem.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            configCardViewListAll();
                        }

                    });
                    mensagem.show();
                }


            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
                mensagem.setTitle("Mensagem");
                mensagem.setMessage("Erro ao se conectar com o SGDB" + error);
                mensagem.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                    }

                });
                mensagem.show();
            }
        });
    }

    public void atualizarPagina() {
        configCardViewListAll();
        Toast.makeText(activity, "Atualizando...", Toast.LENGTH_LONG);
    }

    public void irParaTelaInstituicao(Long id) {  //busca por id no banco para mandar para a tela da instituicao
        AsyncHttpClient client = new AsyncHttpClient();
        lista = new ArrayList<>();
        client.addHeader("user-agent", "Mozilla Chrome");
        client.get(Constantes.IPWebService.IP + "instituicao/listId/"+id, new AsyncHttpResponseHandler() {
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



                    lista.add(instituicao);
                }
                Toast.makeText(activity, "Sucesso: " + lista.size(), Toast.LENGTH_SHORT).show();
                //telaInstituicao(lista);


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

    }
}