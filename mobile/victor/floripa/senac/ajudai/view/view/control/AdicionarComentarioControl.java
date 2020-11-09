package victor.floripa.senac.ajudai.view.view.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.control.Tabs.ComentarioControlTab;
import victor.floripa.senac.ajudai.view.view.model.object.Comentario;
import victor.floripa.senac.ajudai.view.view.model.object.Instituicao;
import victor.floripa.senac.ajudai.view.view.util.Constantes;

public class AdicionarComentarioControl {

    Activity activity;
    TextView tvNome;
    TextView tvComentario;
    RatingBar ratingBar;
    Instituicao instituicao;
    ComentarioControlTab controlTab = new ComentarioControlTab();
    View root;

    public AdicionarComentarioControl(Activity activity) {
        this.activity = activity;
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        instituicao = (Instituicao) activity.getIntent().getSerializableExtra(Constantes.Params.PRM_INSTITUICAO);
        tvNome = activity.findViewById(R.id.nomeComentarioAdd);
        tvComentario = activity.findViewById(R.id.comentarioAdd);
        ratingBar = activity.findViewById(R.id.ratingbarAdd);
    }

    public void cadastrar() {
        try {
            Comentario c = new Comentario();
            c.setNome(tvNome.getText().toString());
            c.setComentario(tvComentario.getText().toString());
            c.setEstrelas((int) ratingBar.getRating());
            c.setInstituicao(instituicao);

            if (!c.getNome().isEmpty() && !c.getComentario().isEmpty()){
                if (!c.getNome().isEmpty() || !c.getComentario().isEmpty()){
                    Intent it = new Intent();
                    it.putExtra(Constantes.Params.PRM_INSTITUICAO, instituicao);
                    it.putExtra(Constantes.Params.PRM_COMENTARIO, c);
                    activity.setResult(activity.RESULT_OK, it);
                    activity.finish();
                } else {
                    final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
                    mensagem.setTitle("Mensagem");
                    mensagem.setMessage("Verifique se todos os campos foram inseridos corretamente.");
                    mensagem.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                        }

                    });
                    mensagem.show();
                }
            } else {
                final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
                mensagem.setTitle("Mensagem");
                mensagem.setMessage("Preencha os campos");
                mensagem.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                    }

                });
                mensagem.show();
            }
        } catch (Exception e) {
            final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
            mensagem.setTitle("Mensagem");
            mensagem.setMessage("Verifique se todos os campos foram inseridos corretamente.");
            mensagem.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                }

            });
            mensagem.show();
        }
    }

    public void cancelar() {
        Intent returnIntent = new Intent();
        activity.setResult(Activity.RESULT_CANCELED, returnIntent);
        activity.finish();
    }


}

